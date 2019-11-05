package seedu.address.logic.commands.deletecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Wish;

/**
 * Deletes a wish entry identified using it's displayed index from the address book.
 */
public class DeleteWishCommand extends Command {

    public static final String COMMAND_WORD = "deleteWish";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the wish identified by the index number used in the displayed wish list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ENTRY_SUCCESS = "Deleted Wish: %1$s";

    private final Index targetIndex;

    public DeleteWishCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Wish> lastShownList = model.getFilteredWishes();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        Wish wishToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteWish(wishToDelete);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_DELETE_ENTRY_SUCCESS, wishToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteWishCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteWishCommand) other).targetIndex)); // state check
    }
}
