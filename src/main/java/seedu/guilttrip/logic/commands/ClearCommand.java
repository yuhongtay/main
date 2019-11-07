package seedu.guilttrip.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.guilttrip.logic.CommandHistory;
import seedu.guilttrip.model.GuiltTrip;
import seedu.guilttrip.model.Model;

/**
 * Clears the guilttrip book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Guilt Trip has been cleared!";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.setAddressBook(new GuiltTrip(true));
        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}