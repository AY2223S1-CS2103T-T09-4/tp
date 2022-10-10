package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AdditionalNotes;
import seedu.address.model.person.Address;
import seedu.address.model.person.Class;
import seedu.address.model.person.Email;
import seedu.address.model.person.MoneyOwed;
import seedu.address.model.person.MoneyPaid;
import seedu.address.model.person.Name;
import seedu.address.model.person.NokPhone;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    /**
     * Constructs a EditPersonDescriptionBuilder object with descriptor initialised.
     * By default, descriptor field Class will have fields date and time to be null.
     */
    public EditPersonDescriptorBuilder() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        editPersonDescriptor.setClass(new Class());
        descriptor = editPersonDescriptor;
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setNokPhone(person.getNokPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setClass(person.getAClass());
        descriptor.setMoneyOwed(person.getMoneyOwed());
        descriptor.setMoneyPaid(person.getMoneyPaid());
        descriptor.setAdditionalNotes(person.getAdditionalNotes());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code NokPhone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNokPhone(String nokPhone) {
        descriptor.setNokPhone(new NokPhone(nokPhone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code moneyOwed} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMoneyOwed(Integer moneyOwed) {
        descriptor.setMoneyOwed(new MoneyOwed(moneyOwed));
        return this;
    }

    /**
     * Sets the {@code Class} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withClass(String classDateTime) throws ParseException {
        descriptor.setClass(ParserUtil.parseClass(classDateTime));
        return this;
    }

    /**
     * Sets the {@code moneyPaid} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMoneyPaid(Integer moneyPaid) {
        descriptor.setMoneyPaid(new MoneyPaid(moneyPaid));
        return this;
    }

    /**
     * Sets the {@code AdditionalNotes} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAdditionalNotes(String additionalNotes) {
        descriptor.setAdditionalNotes(new AdditionalNotes(additionalNotes));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }

}
