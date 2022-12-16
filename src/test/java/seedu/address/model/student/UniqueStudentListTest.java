package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERMEDIATE;
import static seedu.address.model.student.UniqueStudentList.findSlotAfterClass;
import static seedu.address.model.student.UniqueStudentList.findSlotBeforeClass;
import static seedu.address.model.student.UniqueStudentList.findSlotBetweenClasses;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.student.exceptions.StudentNotFoundException;
import seedu.address.model.timerange.TimeRange;
import seedu.address.testutil.StudentBuilder;

public class UniqueStudentListTest {

    private final UniqueStudentList uniqueStudentList = new UniqueStudentList();

    @Test
    public void contains_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.contains(null));
    }

    @Test
    public void contains_studentNotInList_returnsFalse() {
        assertFalse(uniqueStudentList.contains(ALICE));
    }

    @Test
    public void contains_studentInList_returnsTrue() {
        uniqueStudentList.add(ALICE);
        assertTrue(uniqueStudentList.contains(ALICE));
    }

    @Test
    public void contains_studentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStudentList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_INTERMEDIATE)
                .build();
        assertTrue(uniqueStudentList.contains(editedAlice));
    }

    @Test
    public void add_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.add(null));
    }

    @Test
    public void add_duplicateStudent_throwsDuplicateStudentException() {
        uniqueStudentList.add(ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.add(ALICE));
    }

    @Test
    public void setStudent_nullTargetStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(null, ALICE));
    }

    @Test
    public void setStudent_nullEditedStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(ALICE, null));
    }

    @Test
    public void setStudent_targetStudentNotInList_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentList.setStudent(ALICE, ALICE));
    }

    @Test
    public void setStudent_editedStudentIsSameStudent_success() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.setStudent(ALICE, ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(ALICE);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasSameIdentity_success() {
        uniqueStudentList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_INTERMEDIATE)
                .build();
        uniqueStudentList.setStudent(ALICE, editedAlice);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(editedAlice);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasDifferentIdentity_success() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.setStudent(ALICE, BOB);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasNonUniqueIdentity_throwsDuplicateStudentException() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.add(BOB);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudent(ALICE, BOB));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.remove(null));
    }

    @Test
    public void remove_studentDoesNotExist_throwsStudentNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentList.remove(ALICE));
    }

    @Test
    public void remove_existingStudent_removesStudent() {
        uniqueStudentList.add(ALICE);
        uniqueStudentList.remove(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullUniqueStudentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((UniqueStudentList) null));
    }

    @Test
    public void setStudents_uniqueStudentList_replacesOwnListWithProvidedUniqueStudentList() {
        uniqueStudentList.add(ALICE);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        uniqueStudentList.setStudents(expectedUniqueStudentList);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudents((List<Student>) null));
    }

    @Test
    public void setStudents_list_replacesOwnListWithProvidedList() {
        uniqueStudentList.add(ALICE);
        List<Student> studentList = Collections.singletonList(BOB);
        uniqueStudentList.setStudents(studentList);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.add(BOB);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudents_listWithDuplicateStudents_throwsDuplicateStudentException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentList.setStudents(listWithDuplicateStudents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStudentList.asUnmodifiableObservableList().remove(0));
    }

    /**
     * Creates a student with specified name and class time.
     */
    private static Student studentQuickBuilder(Name name, Phone phone, Class aClass) {
        if (phone == null) {
            phone = new Phone("61234567");
        }
        return new Student(name, phone, ALICE.getNokPhone(), ALICE.getEmail(), ALICE.getAddress(), aClass,
                ALICE.getMoneyOwed(), ALICE.getMoneyPaid(), ALICE.getRatesPerClass(), ALICE.getAdditionalNotes(),
                ALICE.getTags(), ALICE.getMarkStatus());
    }

    @Test
    public void findAvailableClassSlotTest() {
        LocalDate date1 = LocalDate.of(2022, 12, 16);
        LocalDate date2 = LocalDate.of(2022, 12, 17);
        LocalDate date3 = LocalDate.of(2022, 12, 18);
        LocalTime time1 = LocalTime.of(10, 0, 0);
        LocalTime time2 = LocalTime.of(11, 0, 0);
        LocalTime time3 = LocalTime.of(12, 0, 0);
        LocalTime time4 = LocalTime.of(13, 0, 0);
        LocalTime time5 = LocalTime.of(14, 0, 0);
        LocalTime time6 = LocalTime.of(15, 0, 0);
        int duration1 = 60;
        int duration2 = 120;
        int duration3 = 300;
        Student s1 = studentQuickBuilder(new Name("s1"), new Phone("61234567"), new Class(date1, time2, time3));
        Student s2 = studentQuickBuilder(new Name("s2"), new Phone("61234568"), new Class(date2, time3, time6));

        assertEquals(
                new Class(date1, time1, time2),
                uniqueStudentList.findAvailableClassSlot(new TimeRange(time1, time6, duration1), date1, time3));
        uniqueStudentList.add(s1);
        assertEquals(
                new Class(date1, time3, time5),
                uniqueStudentList.findAvailableClassSlot(new TimeRange(time1, time6, duration2), date1, time2));
        uniqueStudentList.remove(s1);
        uniqueStudentList.add(s2);
        assertEquals(
                new Class(date1, time2, time4),
                uniqueStudentList.findAvailableClassSlot(new TimeRange(time1, time6, duration2), date1, time2));
        uniqueStudentList.add(s1);
        assertEquals(
                new Class(date1, time3, time5),
                uniqueStudentList.findAvailableClassSlot(new TimeRange(time1, time6, duration2), date1, time2));
        assertEquals(
                new Class(date1, time1, time2),
                uniqueStudentList.findAvailableClassSlot(new TimeRange(time1, time6, duration1), date1, time1));
        assertEquals(
                new Class(date3, time1, time6),
                uniqueStudentList.findAvailableClassSlot(new TimeRange(time1, time6, duration3), date1, time1));


    }
    @Test
    public void findSlotBeforeClassTest() {
        LocalDate date1 = LocalDate.of(2022, 12, 16);
        LocalDate date2 = LocalDate.of(2022, 12, 17);
        LocalDate date3 = LocalDate.of(2022, 12, 18);
        LocalTime time1 = LocalTime.of(10, 0, 0);
        LocalTime time2 = LocalTime.of(11, 0, 0);
        LocalTime time3 = LocalTime.of(12, 0, 0);
        LocalTime time4 = LocalTime.of(13, 0, 0);
        LocalTime time5 = LocalTime.of(14, 0, 0);
        LocalTime time6 = LocalTime.of(15, 0, 0);
        int duration1 = 60;
        int duration2 = 120;
        int duration3 = 180;
        Student s1 = studentQuickBuilder(new Name("s1"), null, new Class(date1, time1, time2));
        Student s2 = studentQuickBuilder(new Name("s2"), null, new Class(date1, time2, time5));
        Student s3 = studentQuickBuilder(new Name("s3"), null, new Class(date2, time3, time6));
        Student s4 = studentQuickBuilder(new Name("s4"), null, new Class(date3, time4, time6));
        assertEquals(
                new Class(date1, time1, time2),
                findSlotBeforeClass(new TimeRange(time1, time6, duration1), date1, time1, s2));
        assertEquals(
                null,
                findSlotBeforeClass(new TimeRange(time2, time6, duration1), date1, time1, s2));
        assertEquals(
                null,
                findSlotBeforeClass(new TimeRange(time1, time6, duration2), date1, time1, s2));
        assertEquals(
                null,
                findSlotBeforeClass(new TimeRange(time1, time6, duration1), date1, time3, s2));
        assertEquals(
                null,
                findSlotBeforeClass(new TimeRange(time1, time6, duration1), date1, time1, s1));
        assertEquals(
                new Class(date1, time3, time4),
                findSlotBeforeClass(new TimeRange(time1, time6, duration1), date1, time3, s3));
        assertEquals(
                new Class(date1, time2, time3),
                findSlotBeforeClass(new TimeRange(time2, time6, duration1), date1, time1, s3));
        assertEquals(
                new Class(date2, time3, time5),
                findSlotBeforeClass(new TimeRange(time3, time6, duration2), date1, time5, s4));
        assertEquals(
                new Class(date2, time1, time2),
                findSlotBeforeClass(new TimeRange(time1, time6, duration1), date1, time6, s3));
        assertEquals(
                null,
                findSlotBeforeClass(new TimeRange(time3, time6, duration2), date1, time5, s3));
    }

    @Test
    public void findSlotBetweenClassesTest() {
        LocalDate date1 = LocalDate.of(2022, 12, 16);
        LocalDate date2 = LocalDate.of(2022, 12, 17);
        LocalDate date3 = LocalDate.of(2022, 12, 18);
        LocalTime time1 = LocalTime.of(10, 0, 0);
        LocalTime time2 = LocalTime.of(11, 0, 0);
        LocalTime time3 = LocalTime.of(12, 0, 0);
        LocalTime time4 = LocalTime.of(13, 0, 0);
        LocalTime time5 = LocalTime.of(14, 0, 0);
        LocalTime time6 = LocalTime.of(15, 0, 0);
        int duration1 = 60;
        int duration2 = 120;
        int duration3 = 180;
        Student s1 = studentQuickBuilder(new Name("s1"), null, new Class(date1, time1, time2));
        Student s2 = studentQuickBuilder(new Name("s2"), null, new Class(date1, time4, time5));
        Student s3 = studentQuickBuilder(new Name("s3"), null, new Class(date2, time3, time6));
        Student s4 = studentQuickBuilder(new Name("s4"), null, new Class(date2, time3, time6));
        Student s5 = studentQuickBuilder(new Name("s5"), null, new Class(date1, time2, time3));
        Student s6 = studentQuickBuilder(new Name("s6"), null, new Class(date3, time2, time3));
        assertEquals(
                null,
                findSlotBetweenClasses(new TimeRange(time1, time6, duration1), s1, s5));
        assertEquals(
                new Class(date1, time3, time5),
                findSlotBetweenClasses(new TimeRange(time1, time6, duration2), s5, s4));
        assertEquals(
                new Class(date2, time1, time3),
                findSlotBetweenClasses(new TimeRange(time1, time6, duration2), s2, s3));
        assertEquals(
                new Class(date1, time3, time6),
                findSlotBetweenClasses(new TimeRange(time3, time6, duration3), s1, s4));
        assertEquals(
                new Class(date1, time2, time3),
                findSlotBetweenClasses(new TimeRange(time1, time6, duration1), s1, s2));
        assertEquals(
                new Class(date2, time1, time4),
                findSlotBetweenClasses(new TimeRange(time1, time6, duration3), s2, s6));
    }
    @Test
    public void findSlotAfterClassTest() {
        LocalDate date1 = LocalDate.of(2022, 12, 16);
        LocalDate date2 = LocalDate.of(2022, 12, 17);
        LocalDate date3 = LocalDate.of(2022, 12, 18);
        LocalTime time1 = LocalTime.of(10, 0, 0);
        LocalTime time2 = LocalTime.of(11, 0, 0);
        LocalTime time3 = LocalTime.of(12, 0, 0);
        LocalTime time4 = LocalTime.of(13, 0, 0);
        LocalTime time5 = LocalTime.of(14, 0, 0);
        LocalTime time6 = LocalTime.of(15, 0, 0);
        int duration1 = 60;
        int duration2 = 120;
        int duration3 = 180;
        Student s1 = studentQuickBuilder(new Name("s1"), null, new Class(date1, time1, time2));
        Student s2 = studentQuickBuilder(new Name("s2"), null, new Class(date1, time4, time5));
        Student s3 = studentQuickBuilder(new Name("s3"), null, new Class(date2, time3, time6));
        assertEquals(
                new Class(date1, time5, time6),
                findSlotAfterClass(new TimeRange(time1, time6, duration1), s2));
        assertEquals(
                new Class(date1, time3, time6),
                findSlotAfterClass(new TimeRange(time3, time6, duration3), s1));
        assertEquals(
                new Class(date3, time3, time5),
                findSlotAfterClass(new TimeRange(time3, time6, duration2), s3));
    }
}
