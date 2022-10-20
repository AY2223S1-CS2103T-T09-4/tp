package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AdditionalNotes;
import seedu.address.model.person.Address;
import seedu.address.model.person.Class;
import seedu.address.model.person.Email;
import seedu.address.model.person.Mark;
import seedu.address.model.person.Money;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_NOK_PHONE = "87654321";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Integer DEFAULT_MONEY_OWED = 0;
    public static final Integer DEFAULT_MONEY_PAID = 0;
    public static final Integer DEFAULT_RATES_PER_CLASS = 40;
    public static final String DEFAULT_ADDITIONAL_NOTES = "";
    public static final Boolean DEFAULT_ATTENDANCE_STATUS = Boolean.FALSE;

    private Name name;
    private Phone phone;
    private Phone nokPhone;
    private Email email;
    private Address address;
    private Money moneyOwed;
    private Money moneyPaid;
    private Money ratesPerClass;
    private AdditionalNotes additionalNotes;
    private Class aClass;
    private Set<Tag> tags;
    private Class displayedClass;
    private Mark mark;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        nokPhone = new Phone(DEFAULT_NOK_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        aClass = new Class();
        moneyOwed = new Money(DEFAULT_MONEY_OWED);
        moneyPaid = new Money(DEFAULT_MONEY_PAID);
        ratesPerClass = new Money(DEFAULT_RATES_PER_CLASS);
        additionalNotes = new AdditionalNotes(DEFAULT_ADDITIONAL_NOTES);
        tags = new HashSet<>();
        displayedClass = aClass;
        mark = new Mark(DEFAULT_ATTENDANCE_STATUS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        nokPhone = personToCopy.getNokPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        aClass = personToCopy.getAClass();
        moneyOwed = personToCopy.getMoneyOwed();
        moneyPaid = personToCopy.getMoneyPaid();
        ratesPerClass = personToCopy.getRatesPerClass();
        additionalNotes = personToCopy.getAdditionalNotes();
        tags = new HashSet<>(personToCopy.getTags());
        displayedClass = personToCopy.getDisplayedClass();
        mark = personToCopy.getMarkStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the next of kin {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withNokPhone(String nokPhone) {
        this.nokPhone = new Phone(nokPhone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Class} of the {@code Person} that we are building.
     */
    public PersonBuilder withClass(String classDateTime) throws ParseException {
        this.aClass = ParserUtil.parseClass(classDateTime);
        return this;
    }

    /**
     * Sets the {@code MoneyOwed} of the {@code Person} that we are building.
     */
    public PersonBuilder withMoneyOwed(Integer moneyOwed) {
        this.moneyOwed = new Money(moneyOwed);
        return this;
    }

    /**
     * Sets the {@code MoneyPaid} of the {@code Person} that we are building.
     */
    public PersonBuilder withMoneyPaid(Integer moneyPaid) {
        this.moneyPaid = new Money(moneyPaid);
        return this;
    }

    /**
     * Sets the {@code ratesPerClass} of the {@code Person} that we are building.
     */
    public PersonBuilder withRatesPerClass(Integer ratesPerClass) {
        this.ratesPerClass = new Money(ratesPerClass);
        return this;
    }

    /**
     * Sets the {@code AdditionalNotes} of the {@code Person} that we are building.
     */
    public PersonBuilder withAdditionalNotes(String additionalNotes) {
        this.additionalNotes = new AdditionalNotes(additionalNotes);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Returns Person object with the fields initialised.
     * @return Person object.
     */
    public Person build() {
        return new
                Person(name, phone, nokPhone, email, address, aClass, moneyOwed, moneyPaid, ratesPerClass,
                additionalNotes, tags, mark, displayedClass);
    }
}
