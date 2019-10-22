package seedu.address.logic.commands.remindercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_WISH_REMINDERS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Description;
import seedu.address.model.person.Wish;
import seedu.address.model.reminder.WishReminder;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing Wish Reminder in the address book.
 */
public class EditWishReminderCommand extends Command {
    public static final String COMMAND_WORD = "editWishReminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the WishReminder identified "
            + "by the index number used in the displayed WishReminder list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DESC + "REMINDER_MESSAGE"
            + PREFIX_AMOUNT + "WISH INDEX \n"
            + PREFIX_TIME + "NEW DATE";

    public static final String MESSAGE_EDIT_ENTRY_SUCCESS = "Edited Expense Reminder: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ENTRY = "This entry already exists in the address book.";

    private final Index index;
    private final EditWishReminderCommand.EditReminderDescriptor editReminderDescriptor;

    /**
     * @param index of the wishReminder in the filtered expense reminder list to edit
     * @param editReminderDescriptor details to edit the person with
     */
    public EditWishReminderCommand(Index index, EditWishReminderCommand.EditReminderDescriptor editReminderDescriptor) {
        requireNonNull(index);
        requireNonNull(editReminderDescriptor);

        this.index = index;
        this.editReminderDescriptor = new EditWishReminderCommand.EditReminderDescriptor(editReminderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<WishReminder> lastShownList = model.getFilteredWishReminders();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        WishReminder entryToEdit = lastShownList.get(index.getZeroBased());
        WishReminder editedEntry = createEditedWishReminder(entryToEdit, editReminderDescriptor, index);

        if (!entryToEdit.isSameReminder(editedEntry) && model.hasWishReminder(editedEntry)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENTRY);
        }

        model.setWishReminder(entryToEdit, editedEntry);
        model.updateFilteredWishReminders(PREDICATE_SHOW_ALL_WISH_REMINDERS);
        return new CommandResult(String.format(MESSAGE_EDIT_ENTRY_SUCCESS, editedEntry));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static WishReminder createEditedWishReminder(
            WishReminder reminderToEdit,
            EditWishReminderCommand.EditReminderDescriptor editEntryDescriptor, Index wishIndex) {
        assert reminderToEdit != null;
        Description updatedMessage = editEntryDescriptor.getDesc().orElse(reminderToEdit.getMessage());
        Wish updatedWish = editEntryDescriptor.getWish().orElse(reminderToEdit.getWish());
        return new WishReminder(updatedMessage, updatedWish, wishIndex);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditWishReminderCommand)) {
            return false;
        }

        // state check
        EditWishReminderCommand e = (EditWishReminderCommand) other;
        return index.equals(e.index)
                && editReminderDescriptor.equals(e.editReminderDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditReminderDescriptor {
        private Description desc;
        private Wish wish;
        private Set<Tag> tags;

        public EditReminderDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditReminderDescriptor(EditWishReminderCommand.EditReminderDescriptor toCopy) {
            setDesc(toCopy.desc);
            setWish(toCopy.wish);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(desc, wish, tags);
        }

        public void setDesc(Description desc) {
            this.desc = desc;
        }

        public Optional<Description> getDesc() {
            return Optional.ofNullable(desc);
        }


        public void setWish(Wish wish) {
            this.wish = wish;
        }

        public Optional<Wish> getWish() {
            return Optional.ofNullable(wish);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditWishReminderCommand.EditReminderDescriptor)) {
                return false;
            }

            // state check
            EditWishReminderCommand.EditReminderDescriptor e = (EditWishReminderCommand.EditReminderDescriptor) other;

            return getDesc().equals(e.getDesc())
                    && getWish().equals(e.getWish());
        }
    }
}
