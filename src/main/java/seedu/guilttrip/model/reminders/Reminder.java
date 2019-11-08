package seedu.guilttrip.model.reminders;

import seedu.guilttrip.commons.util.ListenerSupport;
import seedu.guilttrip.commons.util.ObservableSupport;
import seedu.guilttrip.model.reminders.messages.Message;
import seedu.guilttrip.model.reminders.messages.Notification;

public interface Reminder {
    /**
     * Used by ReminderManager to know when to update displayed reminder messages.
     * @return
     */
    abstract Status getStatus();
    abstract Notification genNotification();
    abstract Message getMessage();
    abstract void reset();
    abstract boolean willDisplayPopUp();
    abstract void togglePopUpDisplay(boolean willDisplayPopup);
    abstract void setMessage(Message message);
    public ObservableSupport getSupport();
    public void addPropertyChangeListener(ListenerSupport pcl);
    public void removePropertyChangeListener(ListenerSupport pcl);

    /**
     * Status of condition
     */
    public enum Status {
        unmet, met, exceeded;

        /**
         * Converts string to status enum.
         */
        public static Status parse (String status) {
            switch (status.toLowerCase()) {
            case "met":
                return met;
            case "exceeded":
                return exceeded;
            default:
                return unmet;
            }
        }
        @Override
        public String toString() {
            switch(this) {
            case met:
                return "met";
            case exceeded:
                return "exceeded";
            default:
                return "unmet";
            }
        }
    }
}
