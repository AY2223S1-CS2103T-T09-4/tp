package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() throws ParseException {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different next of kin phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withNokPhone(VALID_NOK_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different class -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withClass(VALID_CLASS_AMY).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different money owed -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withMoneyOwed(VALID_MONEY_OWED_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different money paid -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withMoneyPaid(VALID_MONEY_PAID_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different additional notes -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAdditionalNotes(VALID_DIFFERENT_ADDITIONAL_NOTES_AMY)
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
