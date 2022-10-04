package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.*;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
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
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setMoneyOwed(person.getMoneyOwed());
        descriptor.setMoneyPaid(person.getMoneyPaid());
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
     * Sets the {@code moneyPaid} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMoneyPaid(Integer moneyPaid) {
        descriptor.setMoneyPaid(new MoneyPaid(moneyPaid));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
