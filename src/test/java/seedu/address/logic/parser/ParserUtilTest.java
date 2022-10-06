package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Class;
import seedu.address.model.person.Email;
import seedu.address.model.person.MoneyOwed;
import seedu.address.model.person.MoneyPaid;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final Integer INVALID_MONEY_OWED = -1;
    private static final Integer INVALID_MONEY_PAID = -1;
    private static final String INVALID_CLASS_DATE_TIME = "2022 05 10 1500-1600";
    private static final String INVALID_EMPTY_CLASS_DATE_TIME = "";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final Integer VALID_MONEY_OWED = 10;
    private static final Integer VALID_MONEY_PAID = 100;
    private static final String VALID_CLASS_DATE_TIME = "2022-05-10 1500-1600";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseMoneyOwed_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMoneyOwed((String) null));
    }

    @Test
    public void parseMoneyOwed_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMoneyOwed(INVALID_MONEY_OWED.toString()));
    }

    @Test
    public void parseMoneyOwed_validValue_returnsMoneyOwed() throws Exception {
        MoneyOwed expectedMoneyOwed = new MoneyOwed(VALID_MONEY_OWED);
        assertEquals(expectedMoneyOwed, ParserUtil.parseMoneyOwed(VALID_MONEY_OWED.toString()));
    }

    @Test
    public void parseMoneyPaid_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseMoneyPaid((String) null));
    }

    @Test
    public void parseMoneyPaid_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMoneyPaid(INVALID_MONEY_PAID.toString()));
    }

    @Test
    public void parseMoneyPaid_validValue_returnsMoneyOwed() throws Exception {
        MoneyPaid expectedMoneyPaid = new MoneyPaid(VALID_MONEY_PAID);
        assertEquals(expectedMoneyPaid, ParserUtil.parseMoneyPaid(VALID_MONEY_PAID.toString()));
    }

    @Test
    public void parseClassDateTime_validDateTime_returnsClass() throws Exception {
        Class expectedClass = new Class(LocalDate.of(2022, 5, 10),
                LocalTime.of(15, 0), LocalTime.of(16, 0), VALID_CLASS_DATE_TIME);
        assertEquals(expectedClass, ParserUtil.parseClass(VALID_CLASS_DATE_TIME));
    }

    @Test
    public void parseClassDateTime_invalidDateTime_throwsParseException() throws Exception {
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(INVALID_CLASS_DATE_TIME));
        assertThrows(ParseException.class, () -> ParserUtil.parseClass(INVALID_EMPTY_CLASS_DATE_TIME));
    }

}
