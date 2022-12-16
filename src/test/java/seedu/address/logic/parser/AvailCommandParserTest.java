package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AvailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.timerange.TimeRange;

public class AvailCommandParserTest {
    private AvailCommandParser parser = new AvailCommandParser();

    @Test
    public void parse_validArgs_returnsAvailCommand() {
        LocalTime t1 = LocalTime.of(10, 0);
        LocalTime t2 = LocalTime.of(11, 0);
        Integer duration = 30;
        assertParseSuccess(parser, " 1000-1100 30", new AvailCommand(new TimeRange(t1, t2, duration)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " UNKNOWN",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AvailCommand.MESSAGE_USAGE));
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AvailCommand.MESSAGE_USAGE), () -> parser.parse(AvailCommand.COMMAND_WORD + " UNKNOWN"));
    }
}
