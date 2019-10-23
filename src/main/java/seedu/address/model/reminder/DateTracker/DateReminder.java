package seedu.address.model.reminder;

import java.time.LocalDate;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Date;
import seedu.address.model.person.Description;
import seedu.address.model.person.Wish;

/**
 * Implements wish reminder
 */
public class DateReminder extends Reminder {
    private Wish wish;
    private Index wishIndex;

    public DateReminder(Description desc, Wish wish, Index wishIndex) {
        super(desc);
        this.wish = wish;
        this.wishIndex = wishIndex;
    }

    /**
     * USed when loading wish reminder from storage. As such, reminder has yet to be mapped to wishes.
     * @return
     */
    public DateReminder(Description desc, Index wishIndex) {
        super(desc);
        this.wishIndex = wishIndex;
    }

    public Wish getWish() {
        return wish;
    }
    public Index getWishIndex() {
        return wishIndex;
    }
    /**
     *checks status of reminder. i.e. should reminder trigger.
     */
    public void updateStatus() {
        Date date = wish.getDate();
        super.setStatus(!LocalDate.now().isBefore(date.getDate()));
    }

    public void setWishIndex(Index index) {
        wishIndex = index;
    }

    public void setWish(Wish wish) {
        assert (wish.equals(wish));
        this.wish = wish;
    }
    @Override
    /**
     * Returns true if both WishReminders have all identity fields that are the same.
     * @param otherReminder DateReminder to compare to
     * @return boolean
     */
    public boolean isSameReminder(Reminder otherReminder) {
        if (otherReminder == this) {
            return true;
        }
        if (!(otherReminder instanceof DateReminder)) {
            return false;
        }
        DateReminder otherDateReminder = (DateReminder) otherReminder;
        return otherDateReminder != null
                && otherDateReminder.getMessage().equals(getMessage())
                && otherDateReminder.getWish().equals(getWish());
    }
}
