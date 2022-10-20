package seedu.address.model.person;

/**
 * Represents a Person's additional Notes in the address book.
 * Guarantees: immutable.
 */
public class AdditionalNotes {

    public String notes;

    /**
     * Constructs an {@code AdditionalNotes}.
     *
     * @param additionalNotes It can be empty or non-empty.
     */
    public AdditionalNotes(String additionalNotes) {
        notes = additionalNotes;
    }

    /**
     * Appends the notes to the current notes.
     *
     * @param appendedAdditionalNotes AdditionalNotes object that contains extra notes to be appended.
     */
    public void appendNotes(AdditionalNotes appendedAdditionalNotes) {
        this.notes += " " + appendedAdditionalNotes.notes;
    }

    @Override
    public String toString() {
        return notes;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AdditionalNotes // instanceof handles nulls
                && notes.equals(((AdditionalNotes) other).notes)); // state check
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }
}
