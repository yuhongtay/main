package seedu.address.logic.commands.remindercommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Description;
import seedu.address.model.person.Wish;
import seedu.address.model.reminder.WishReminder;


/**
 * Adds a person to the address book.
 */
public class AddWishReminderCommand extends Command {

    public static final String COMMAND_WORD = "addWishReminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Expense Reminder to reminders list. "
            + "Parameters: "
            + PREFIX_DESC + "REMINDER_MESSAGE"
            + PREFIX_AMOUNT + "WISH INDEX "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESC + "Birthday gift"
            + PREFIX_AMOUNT + "2 ";

    public static final String MESSAGE_SUCCESS = "New WishReminder added: %1$s";

    private final Description descToAdd;

    private final Index indexToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddWishReminderCommand(Description desc, Index wishIndex) {
        requireNonNull(desc);
        requireNonNull(wishIndex);
        descToAdd = desc;
        indexToAdd = wishIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Wish> lastShownList = model.getFilteredWishes();
        Wish wishToAdd = lastShownList.get(indexToAdd.getZeroBased());
        WishReminder toAdd = new WishReminder(descToAdd, wishToAdd, indexToAdd);
        model.addWishReminder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddWishReminderCommand // instanceof handles nulls
                    && descToAdd.equals(((AddWishReminderCommand) other).descToAdd)
                && indexToAdd == ((AddWishReminderCommand) other).indexToAdd);
    }
}
