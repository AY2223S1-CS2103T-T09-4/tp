package seedu.address.model;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Person;

/**
 * A calculator that calculates the statistics of a particular TeachersPet.
 */
public class StatisticsCalculator {
    private static final Logger logger = LogsCenter.getLogger(StatisticsCalculator.class);

    private final ReadOnlyTeachersPet teachersPet;

    /**
     * Constructs an {@code StatisticsCalculator}.
     *
     * @param teachersPet TeachersPet used to calculate the statistics.
     */
    public StatisticsCalculator(ReadOnlyTeachersPet teachersPet) {
        this.teachersPet = teachersPet;
    }

    /**
     * Calculates the number of people in TeachersPet.
     *
     * @return the number of people stored in TeachersPet.
     */
    public int getSize() {
        return teachersPet.getPersonList().size();
    }

    /**
     * Sums up the total money owed by the people in TeachersPet.
     *
     * @return the total amount of money owed.
     */
    public String getAmountOwed() {
        ObservableList<Person> personList = teachersPet.getPersonList();
        int moneyOwed = 0;
        try {
            for (Person person : personList) {
                moneyOwed = Math.addExact(moneyOwed, person.getMoneyOwed().value);
            }
        } catch (ArithmeticException e) {
            return "Owed amount too large to calculate.";
        }
        return "$" + String.valueOf(moneyOwed);
    }

    /**
     * Sums up the total money paid by the people in TeachersPet.
     *
     * @return the total amount of money paid.
     */
    public String getAmountPaid() {
        ObservableList<Person> personList = teachersPet.getPersonList();
        int moneyPaid = 0;
        try {
            for (Person person : personList) {
                moneyPaid = Math.addExact(moneyPaid, person.getMoneyPaid().value);
            }
        } catch (ArithmeticException e) {
            return "Paid amount too large to calculate.";
        }
        return "$" + String.valueOf(moneyPaid);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatisticsCalculator // instanceof handles nulls
                && teachersPet.equals(((StatisticsCalculator) other).teachersPet));
    }
}
