package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.reminder.Reminder;


/**
 * Panel containing the list of reminders.
 */
public class RemindersListPannel extends UiPart<Region> {
    private static final String FXML = "RemindersListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EntryListPanel.class);

    @javafx.fxml.FXML
    private ListView<Reminder> reminderListView;

    public RemindersListPanel(ObservableList<Reminder> entryList) {
        super(FXML);
        reminderListView.setItems(entryList);
        reminderListView.setCellFactory(listView -> new ReminderListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ReminderListViewCell extends ListCell<Reminder> {
        @Override
        protected void updateItem(Reminder reminder, boolean empty) {
            super.updateItem(reminder, empty);

            if (empty || reminder == null) {

                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ReminderCard(reminder, getIndex() + 1).getRoot());
            }
        }
    }

}
