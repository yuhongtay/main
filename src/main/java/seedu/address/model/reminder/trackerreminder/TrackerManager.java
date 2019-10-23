package seedu.address.model.reminder.trackerreminder;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.person.Expense;

/**
 * Manages TrackerList. Will be instantiated inside Object Manager and Address Book.
 */
public class ExpenseTrackerManager {
    private FilteredList<Tracker> trackerList;



    public ExpenseTrackerManager(ObservableList<Tracker> trackerList) {
        this.trackerList = new FilteredList<>(trackerList);
    }


    public ObservableList<Tracker> getList() {
        return trackerList;
    }

    /**
     * Iterates through list of trackers, and see which tracker(s) are keeping track of this event.
     * @param filteredExpenses
     */
    public void track(FilteredList<Expense> filteredExpenses) {
        for (Tracker tracker : trackerList) {
            long newAmt = 0;
            filteredExpenses.setPredicate(tracker.getPredicate());
            for (Expense expense : filteredExpenses) {
                newAmt += expense.getAmount().value;
            }
            tracker.setAmount(newAmt);
        }
    }
}
