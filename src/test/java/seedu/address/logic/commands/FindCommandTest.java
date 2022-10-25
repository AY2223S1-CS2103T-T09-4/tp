package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalTeachersPet;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicate.ClassContainsKeywordsPredicate;
import seedu.address.model.person.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTeachersPet(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTeachersPet(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        AddressContainsKeywordsPredicate addressFirstPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("121 Baker Street #100-@-99"));
        AddressContainsKeywordsPredicate addressSecondPredicate =
                new AddressContainsKeywordsPredicate(Collections.singletonList("(00 - 12379623) Prinsep :1 Lane"));
        ClassContainsKeywordsPredicate classOnePredicate =
                new ClassContainsKeywordsPredicate(Collections.singletonList("2022-10-10"));
        ClassContainsKeywordsPredicate classTwoPredicate =
                new ClassContainsKeywordsPredicate(Collections.singletonList("2022-10-11"));
        EmailContainsKeywordsPredicate emailFirstPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("fong_teng@yahoo.com"));
        EmailContainsKeywordsPredicate emailSecondPredicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("wongtf@gmail.com"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);
        FindCommand findAddressFirstCommand = new FindCommand(addressFirstPredicate);
        FindCommand findAddressSecondCommand = new FindCommand(addressSecondPredicate);
        FindCommand findClassOneCommand = new FindCommand(classOnePredicate);
        FindCommand findClassTwoCommand = new FindCommand(classTwoPredicate);
        FindCommand findEmailFirstCommand = new FindCommand(emailFirstPredicate);
        FindCommand findEmailSecondCommand = new FindCommand(emailSecondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        assertTrue(findAddressFirstCommand.equals(findAddressFirstCommand));
        assertTrue(findClassOneCommand.equals(findClassOneCommand));
        assertTrue(findEmailFirstCommand.equals(findEmailFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        FindCommand findAddressFirstCommandCopy = new FindCommand(addressFirstPredicate);
        assertTrue(findAddressFirstCommand.equals(findAddressFirstCommandCopy));
        FindCommand findClassOneCommandCopy = new FindCommand(classOnePredicate);
        assertTrue(findClassOneCommand.equals(findClassOneCommandCopy));
        FindCommand findEmailFirstCommandCopy = new FindCommand(emailFirstPredicate);
        assertTrue(findEmailFirstCommand.equals(findEmailFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));
        assertFalse(findAddressFirstCommand.equals(2));
        assertFalse(findSecondCommand.equals(1));
        assertFalse(findEmailFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));
        assertFalse(findAddressFirstCommand.equals(null));
        assertFalse(findClassOneCommand.equals(null));
        assertFalse(findEmailFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // different address -> returns false
        assertFalse(findAddressFirstCommand.equals(findAddressSecondCommand));

        // different date -> returns false
        assertFalse(findClassOneCommand.equals(findClassTwoCommand));

        // different address -> returns false
        assertFalse(findEmailFirstCommand.equals(findEmailSecondCommand));
    }

    @Test
    public void execute_zeroNameKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroAddressKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        AddressContainsKeywordsPredicate predicate = prepareAddressPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroClassKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        ClassContainsKeywordsPredicate predicate = prepareClassPredicate("2022-01-01");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroEmailKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        EmailContainsKeywordsPredicate predicate = prepareEmailPredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into an {@code AddressContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ClassContainsKeywordsPredicate}.
     */
    private ClassContainsKeywordsPredicate prepareClassPredicate(String userInput) {
        return new ClassContainsKeywordsPredicate(Arrays.asList(userInput));
    }

    /**
     * Parses {@code userInput} into an {@code EmailContainsKeywordsPredicate}.
     */
    private EmailContainsKeywordsPredicate prepareEmailPredicate(String userInput) {
        return new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
