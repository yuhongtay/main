package seedu.address.model.reminder;

import seedu.address.model.person.Description;

/**
 * Implement expense reminder
 */
public class TrackerReminder extends Reminder {
    private long currSum;
    private long quota;
    private ExpenseTracker tracker;

    public TrackerReminder(Description message, long quota, ExpenseTracker tracker) {
        super(message);
        this.tracker = tracker;
        this.quota = quota;
        currSum = tracker.getAmount();
    }


    public long getSum() {
        return currSum;
    }

    public long getQuota() {
        return quota;
    }

    public ExpenseTracker getTracker() {
        return tracker;
    }

    /**
     *checks status of reminder. i.e. should reminder trigger.
     */
    public void updateStatus() {
        currSum = tracker.getAmount();
        super.setStatus(currSum >= quota);
    }

    @Override
    /**
     * Returns true if both ExpenseReminders have all identity fields that are the same.
     * @param otherExpenseReminder TrackerReminder to compare to
     * @return boolean
     */
    public boolean isSameReminder(Reminder otherReminder) {
        if (otherReminder == this) {
            return true;
        }
        if (!(otherReminder instanceof TrackerReminder)) {
            return false;
        }
        TrackerReminder otherTrackerReminder = (TrackerReminder) otherReminder;
        return otherTrackerReminder != null
                && otherTrackerReminder.getMessage().equals(getMessage())
                && otherTrackerReminder.getTracker().equals(getTracker())
                && otherTrackerReminder.getQuota() == (getQuota());
    }
}
