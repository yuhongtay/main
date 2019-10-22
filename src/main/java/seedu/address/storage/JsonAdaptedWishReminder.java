package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Description;
import seedu.address.model.person.Entry;
import seedu.address.model.person.Wish;
import seedu.address.model.reminder.WishReminder;

/**
 * Jackson-friendly version of {@link Entry}.
 */
class JsonAdaptedWishReminder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expense's %s field is missing!";

    private String message;
    private Index wishIndex;


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedWishReminder(@JsonProperty("desc") String desc,
                                      @JsonProperty("wishIndex") Index wishIndex) {
        this.message = desc;
        this.wishIndex = wishIndex;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedWishReminder(WishReminder source) {
        message = source.getMessage().toString();
        wishIndex = source.getWishIndex();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public WishReminder toModelType() throws IllegalValueException {
        if (message == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        final String modelMessage = message;
        final Index modelWishIndex = wishIndex;
        return new WishReminder(new Description(modelMessage), modelWishIndex);
    }

}
