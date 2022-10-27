package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all students in student list whose details contain any of the argument keywords/number in a prefix.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons with keywords in a specific prefix. "
            + "Prefix supported by this search includes: n/, p/, np/, e/, a/, dt/, t/. "
            + "The specified keywords are case-insensitive, which displays students as a list with index numbers.\n"
            + "Parameters: KEYWORD [n/KEYWORD] [p/PHONE] [np/NOK_PHONE] [e/KEYWORD] [a/KEYWORD] [dt/DATE] [t/TAG]\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie";

    public static final String ONLY_ONE_PREFIX_MESSAGE = "You can only search with 1 prefix, "
            + "either n/, p/, np/, e/, a/, dt/ or t/";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
