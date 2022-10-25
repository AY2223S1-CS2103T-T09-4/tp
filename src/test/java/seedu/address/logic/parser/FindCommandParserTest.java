package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NOK_PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.Class;
import seedu.address.model.person.predicate.ClassContainsKeywordsPredicate;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    private static final String ONLY_ONE_PREFIX_MESSAGE = "You can only search with 1 prefix, "
            + "either n/, p/, np/, e/, a/ or dt/";
    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        assertParseFailure(parser, "     ",
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingPrefix_failure() {
        // Only name specified without prefix
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // Only phone numbers specified without prefix
        assertParseFailure(parser, "99887766", MESSAGE_INVALID_FORMAT);

        // Empty string parsed
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_multiplePrefixes_failure() {
        // Name and phone prefixes parsed
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY, ONLY_ONE_PREFIX_MESSAGE);

        // Name, phone and email prefixes parsed
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY, ONLY_ONE_PREFIX_MESSAGE);
    }

    @Test
    public void parse_validNamePrefix_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/Alice    Bob", expectedFindCommand);
    }

    @Test
    public void parse_validPhonePrefix_returnsFindCommand() {
        // TODO: Update test case with p/ prefix
        assertParseFailure(parser, PHONE_DESC_AMY, "p/ search not implemented yet.");
    }

    @Test
    public void parse_validEmailPrefix_returnsFindCommand() {
        // TODO: Update test case with e/ prefix
        assertParseFailure(parser, EMAIL_DESC_AMY, "e/ search not implemented yet.");
    }

    @Test
    public void parse_validAddressPrefix_returnsFindCommand() {
        // TODO: Update test case with a/ prefix
        assertParseFailure(parser, ADDRESS_DESC_AMY, "a/ search not implemented yet.");
    }

    @Test
    public void parse_validNokPhonePrefix_returnsFindCommand() {
        // TODO: Update test case with np/ prefix
        assertParseFailure(parser, NOK_PHONE_DESC_AMY, "np/ search not implemented yet.");
    }

    @Test
    public void parse_validClassDate_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new ClassContainsKeywordsPredicate(Arrays.asList("2022-10-10")));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " dt/2022-10-10", expectedFindCommand);

        // trailing whitespaces
        assertParseSuccess(parser, " dt/2022-10-10  ", expectedFindCommand);

        // leading whitespaces
        assertParseSuccess(parser, " dt/   2022-10-10  ", expectedFindCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " dt/   2022-10-10  ", expectedFindCommand);
    }

    @Test
    public void parse_invalidDate() {
        // different format
        assertParseFailure(parser, " dt/10 oct", Class.INVALID_DATETIME_ERROR_MESSAGE);

        // incomplete date format
        assertParseFailure(parser, " dt/2022-10", Class.INVALID_DATETIME_ERROR_MESSAGE);
        assertParseFailure(parser, " dt/2022", Class.INVALID_DATETIME_ERROR_MESSAGE);

        // empty
        assertParseFailure(parser, " dt/", Class.INVALID_DATETIME_ERROR_MESSAGE);
    }

}
