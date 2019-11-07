package seedu.guilttrip.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.guilttrip.commons.core.step.Step;
import seedu.guilttrip.logic.CommandHistory;
import seedu.guilttrip.logic.commands.exceptions.CommandException;
import seedu.guilttrip.model.Model;

/**
 * Reverts the {@code model}'s finance tracker to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "There are insufficient commands to redo!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redo the most recent undone command that modified the state of the finance tracker\n"
            + "If number n is provided, the most recent n number of commands will be redone\n"
            + "Parameters: NUMBER (must be a positive integer)\n"
            + "Example 1: " + COMMAND_WORD + "\n"
            + "Example 2: " + COMMAND_WORD + " 2";

    public final Step step;

    public RedoCommand(Step step) {
        this.step = step;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoAddressBook(step)) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        for (long l = 0; l < step.value; l++) {
            model.redoAddressBook();
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}