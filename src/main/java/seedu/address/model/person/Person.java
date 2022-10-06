package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final MoneyOwed moneyOwed;
    private final MoneyPaid moneyPaid;
    private final AdditionalNotes additionalNotes;
    private final Class aClass;

    /**
     * Constructs a {@code Person} class when first initialized with add command.
     * // Todo: Note that this is a temporary design and subject to update in future iteration
     */
    public Person(Name name, Phone phone, Email email, Address address) {
        requireAllNonNull(name, phone, email, address);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.moneyOwed = new MoneyOwed();
        this.moneyPaid = new MoneyPaid();
        this.additionalNotes = new AdditionalNotes("");
        this.aClass = new Class("");
    }

    /**
     * Overloaded constructor.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  MoneyOwed moneyOwed, MoneyPaid moneyPaid, AdditionalNotes additionalNotes, Class aClass) {
        requireAllNonNull(name, phone, email, address, additionalNotes, aClass);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.moneyOwed = moneyOwed;
        this.moneyPaid = moneyPaid;
        this.additionalNotes = additionalNotes;
        this.aClass = aClass;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public MoneyOwed getMoneyOwed() {
        return moneyOwed;
    }

    public MoneyPaid getMoneyPaid() {
        return moneyPaid;
    }

    public AdditionalNotes getAdditionalNotes() {
        return additionalNotes;
    }

    public Class getAClass() {
        return aClass;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;

        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getMoneyOwed().equals(getMoneyOwed())
                && otherPerson.getMoneyPaid().equals(getMoneyPaid())
                && otherPerson.getAdditionalNotes().equals(getAdditionalNotes())
                && otherPerson.getAClass().toString().equals(getAClass().toString());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, moneyOwed, moneyPaid, additionalNotes, aClass);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Class Date: ")
                .append(getAClass().toString())
                .append("; Money Owed: ")
                .append(getMoneyOwed())
                .append("; Money Paid: ")
                .append(getMoneyPaid())
                .append("; Additional notes: ")
                .append(getAdditionalNotes());

        return builder.toString();
    }
}
