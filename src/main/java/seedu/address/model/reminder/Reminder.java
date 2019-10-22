package seedu.address.model.reminder;

import java.util.function.Predicate;

import seedu.address.model.person.Description;

/**
 * Basic reminder class with minimal functionality.
 */
public abstract class Reminder {
    //private int priority;
    //private ReminderType type;
    private Description message;
    private boolean isActivated;
    private Predicate activationCondition;


    public Reminder(Description message) {
        this.message = message;
        isActivated = false;
    }

    public Description getMessage() {
        return message;
    }

    /*public void setPriority(int priority) {
        this.priority = priority;
    }*/

    public boolean getStatus() {
        return isActivated;
    }

    void setStatus(boolean bool) {
        isActivated = bool;
    }


    @Override
    public String toString() {
        return "(" + isActivated + ") " + message;
    }

    abstract boolean isSameReminder(Reminder reminder);
}
