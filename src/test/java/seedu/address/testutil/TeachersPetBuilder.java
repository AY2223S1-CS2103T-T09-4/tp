package seedu.address.testutil;

import seedu.address.model.TeachersPet;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building TeachersPet objects.
 * Example usage: <br>
 *     {@code TeachersPet ab = new TeachersPetBuilder().withPerson("John", "Doe").build();}
 */
public class TeachersPetBuilder {

    private TeachersPet teachersPet;

    public TeachersPetBuilder() {
        teachersPet = new TeachersPet();
    }

    public TeachersPetBuilder(TeachersPet teachersPet) {
        this.teachersPet = teachersPet;
    }

    /**
     * Adds a new {@code Person} to the {@code TeachersPet} that we are building.
     */
    public TeachersPetBuilder withPerson(Person person) {
        teachersPet.addPerson(person);
        return this;
    }

    public TeachersPet build() {
        return teachersPet;
    }
}
