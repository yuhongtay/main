package seedu.address.logic.commands.remindercommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.WishReminder;

/**
 * delete a wish reminder identified using its displayed index in the address book.
 */
public class DeleteWishReminderCommand extends Command {

    public static final String COMMAND_WORD = "deleteWishReminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the wish reminder identified by the index number used in the displayed reminder list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ENTRY_SUCCESS = "Deleted Reminder: %1$s";

    private final Index targetIndex;

    public DeleteWishReminderCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<WishReminder> lastShownList = model.getFilteredWishReminders();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        WishReminder entryToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteWishReminder(entryToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ENTRY_SUCCESS, entryToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteWishReminderCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteWishReminderCommand) other).targetIndex)); // state check
    }
}