package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.student.exceptions.StudentNotFoundException;
import seedu.address.model.timerange.TimeRange;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls.
 * A student is considered unique by comparing using {@code Student#isSameStudent(Student)}.
 * As such, adding and updating of students uses Student#isSameStudent(Student) for equality
 * to ensure that the student being added or updated is unique in terms of identity in the UniqueStudentList.
 * However, the removal of a student uses Student#equals(Object) so the student with exactly the same fields will be
 * removed.
 * Supports a minimal set of list operations.
 *
 * @see Student#isSameStudent(Student)
 */
public class UniqueStudentList implements Iterable<Student> {

    private final ObservableList<Student> internalList = FXCollections.observableArrayList();
    private final ObservableList<Student> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent student as the given argument.
     */
    public boolean contains(Student toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStudent);
    }

    /**
     * Adds a student to the list.
     * The student must not already exist in the list.
     */
    public void add(Student toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStudentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StudentNotFoundException();
        }

        if (!target.isSameStudent(editedStudent) && contains(editedStudent)) {
            throw new DuplicateStudentException();
        }

        internalList.set(index, editedStudent);
    }

    /**
     * Removes the equivalent student from the list.
     * The student must exist in the list.
     */
    public void remove(Student toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StudentNotFoundException();
        }
    }

    public void setStudents(UniqueStudentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        requireAllNonNull(students);
        if (!studentsAreUnique(students)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(students);
    }

    /**
     * Sorts the {@code internalList} by the given {@code comparator}.
     */
    public void sortStudents(Comparator<Student> comparator) {
        requireNonNull(comparator);
        ArrayList<Student> sortedList = replaceSort(internalList, comparator);
        internalList.setAll(sortedList);
    }


    private static ArrayList<Student> replaceSort(
            ObservableList<Student> observableList, Comparator<Student> comparator) {
        ArrayList<Student> duplicatedList = new ArrayList<>();
        for (int i = 0; i < observableList.size(); i++) {
            duplicatedList.add(observableList.get(i));
        }
        duplicatedList.sort(comparator);
        return duplicatedList;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Student> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns the current list.
     */
    public ObservableList<Student> getInternalList() {
        return internalList;
    }

    @Override
    public Iterator<Student> iterator() {
        return internalList.iterator();
    }

    /**
     * Finds the next available time slot given a valid {@code TimeRange timeRange}.
     * @param timeRange the stipulated time range that an available time slot should be found from
     * @return a valid class that fulfills the requirement by the time range
     */
    public Class findAvailableClassSlot(TimeRange timeRange) {
        LocalDate currDate = LocalDate.now();
        LocalTime currTime = LocalTime.now();
        // find all students with class from today current time onwards
        List<Student> list = internalList
                .stream()
                .filter(student -> student.getAClass().startTime != null
                        && student.getAClass().endTime != null
                        && student.getAClass().date != null
                        && student.getAClass().date.compareTo(currDate) >= 0
                        && student.getAClass().startTime.compareTo(LocalTime.now()) >= 0)
                .sorted(Student::compareToByClassStartTimeAsc)
                .collect(Collectors.toList());
        if (list.size() == 0) {
            return new Class(
                    currDate, timeRange.startTimeRange, timeRange.startTimeRange.plusMinutes(timeRange.duration));
        } else if (list.size() == 1) {
            return findSlotWithSingleRecord(timeRange, currDate, currTime, list.get(0));
        } else {
            return findSlotWithMultipleRecord(timeRange, currDate, currTime, list);
        }
    }

    private static Class findSlotWithSingleRecord(
            TimeRange tr, LocalDate currDate, LocalTime currTime, Student student) {
        Class newClass;
        Class classToCompare = student.getAClass();
        // When the startTimeRange is before the earliest slot
        assert classToCompare.endTime != null;
        assert classToCompare.startTime != null;

        newClass = findSlotBeforeClass(tr, currDate, currTime, student);
        if (newClass != null) {
            return newClass;
        }
        newClass = findSlotAfterClass(tr, student);
        assert newClass != null;
        return newClass;
    }

    private static Class findSlotWithMultipleRecord(
            TimeRange tr, LocalDate currDate, LocalTime currTime, List<Student> students) {
        Class newClass;
        // check time before first student
        newClass = findSlotBeforeClass(tr, currDate, currTime, students.get(0));
        if (newClass != null) {
            return newClass;
        }

        // check time between each pair of neighbouring students
        for (int i = 0; i < students.size() - 1; i++) {
            Student s1 = students.get(i);
            Student s2 = students.get(i + 1);
            newClass = findSlotBetweenClasses(tr, s1, s2);
            if (newClass != null) {
                return newClass;
            }
        }

        // check time after last student
        newClass = findSlotAfterClass(tr, students.get(students.size() - 1));
        return newClass;
    }

    private static Class findSlotBeforeClass(TimeRange tr, LocalDate currDate, LocalTime currTime, Student student) {
        Class classToCompare = student.getAClass();
        assert currDate.compareTo(classToCompare.date) <= 0;
        assert tr.startTimeRange.plusMinutes(tr.duration).compareTo(tr.endTimeRange) <= 0;

        if (currDate.compareTo(classToCompare.date) == 0) {
            //  if the currDate is same as next class date:
            //      find the bigger time between currTime and tr.startTimeRange
            //      and check whether there can be a slot from then.
            LocalTime earliestStartTime;
            if (currTime.compareTo(tr.startTimeRange) < 0) {
                earliestStartTime = tr.startTimeRange;
            } else {
                earliestStartTime = currTime;
            }
            if (earliestStartTime.plusMinutes(tr.duration).compareTo(classToCompare.startTime) <= 0) {
                return new Class(currDate, earliestStartTime, earliestStartTime.plusMinutes(tr.duration));
            } else {
                return null;
            }
        }

        // by assertion: currDate.compareTo(classToCompare.date) <= 0, currDate must be before the classToCompare.date

        // check on whether possible to have a class on currDate
        if (currTime.compareTo(tr.startTimeRange) < 0) {
            return new Class(currDate, tr.startTimeRange, tr.startTimeRange.plusMinutes(tr.duration));
        } else if (currTime.compareTo(tr.endTimeRange) < 0) {
            if (currTime.plusMinutes(tr.duration).compareTo(tr.endTimeRange) <= 0) {
                return new Class(currDate, currTime, currTime.plusMinutes(tr.duration));
            }
        }

        assert currDate.plusDays(1).compareTo(classToCompare.date) <= 0;
        // check on whether possible to have a class on next day of currDate
        if (currDate.plusDays(1).compareTo(classToCompare.date) < 0) {
            // currDate + 1 day is still before the given class date
            return new Class(currDate.plusDays(1),
                    tr.startTimeRange, tr.startTimeRange.plusMinutes(tr.duration));
        } else {
            // by assertion: currDate.plusDays(1).compareTo(classToCompare.date) <= 0
            // currDate.plusDays(1).compareTo(classToCompare.date) == 0 in this part
            if (tr.startTimeRange.plusMinutes(tr.duration).compareTo(classToCompare.startTime) <= 0) {
                return new Class(currDate.plusDays(1),
                        tr.startTimeRange, tr.startTimeRange.plusMinutes(tr.duration));
            } else {
                return null;
            }
        }
    }

    private static Class findSlotBetweenClasses(TimeRange tr, Student student1, Student student2) {
        Class c1 = student1.getAClass();
        Class c2 = student2.getAClass();
        assert c1.date.compareTo(c2.date) <= 0;

        // used for same-day-check and after-c1-slot-check
        LocalTime earliestStartTime;
        if (c1.endTime.compareTo(tr.startTimeRange) < 0) {
            earliestStartTime = tr.startTimeRange;
        } else {
            earliestStartTime = c1.endTime;
        }

        if (c1.date.compareTo(c2.date) == 0) {
            // if c1 and c2 on same day, simply check gap between
            if (earliestStartTime.plusMinutes(tr.duration).compareTo(c2.startTime) <= 0) {
                return new Class(c1.date, earliestStartTime, earliestStartTime.plusMinutes(tr.duration));
            }
        } else {
            // check slot after c1
            if (earliestStartTime.plusMinutes(tr.duration).compareTo(tr.endTimeRange) <= 0) {
                return new Class(c1.date, earliestStartTime, earliestStartTime.plusMinutes(tr.duration));
            }
            // check whether there is gap in days between c1 and c2
            if (c1.date.plusDays(1).compareTo(c2.date) < 0) {
                return new Class(
                        c1.date.plusDays(1), tr.startTimeRange, tr.startTimeRange.plusMinutes(tr.duration));
            }
            // check slot before c2
            if (tr.startTimeRange.plusMinutes(tr.duration).compareTo(c2.startTime) <= 0) {
                return new Class(c2.date, tr.startTimeRange, tr.startTimeRange.plusMinutes(tr.duration));
            }
        }
        return null;
    }
    private static Class findSlotAfterClass(TimeRange tr, Student student) {
        Class classToCompare = student.getAClass();
        if (classToCompare.endTime.compareTo(tr.startTimeRange) < 0) {
            return new Class(classToCompare.date, tr.startTimeRange, tr.startTimeRange.plusMinutes(tr.duration));
        } else if (classToCompare.endTime.plusMinutes(tr.duration).compareTo(tr.endTimeRange) <= 0) {
            return new Class(
                    classToCompare.date, classToCompare.endTime, classToCompare.endTime.plusMinutes(tr.duration));
        } else {
            return new Class(classToCompare.date.plusDays(1),
                    tr.startTimeRange, tr.startTimeRange.plusMinutes(tr.duration));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueStudentList // instanceof handles nulls
                        && internalList.equals(((UniqueStudentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code students} contains only unique students.
     */
    private boolean studentsAreUnique(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(i).isSameStudent(students.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
