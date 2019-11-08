package seedu.guilttrip.model.reminders.conditions;

import static java.util.Objects.requireNonNull;
import static seedu.guilttrip.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.guilttrip.model.entry.Expense;
import seedu.guilttrip.model.entry.ExpenseList;
import seedu.guilttrip.model.entry.exceptions.DuplicateEntryException;
import seedu.guilttrip.model.entry.exceptions.EntryNotFoundException;
import seedu.guilttrip.model.reminders.Reminder;

public class ConditionList implements Iterable<Condition>{

    final List<Condition> internalList = new ArrayList();

    /**
     * Returns true if the list contains an equivalent entry as the given argument.
     */
    public boolean contains(Condition toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a entry to the list.
     * The entry must not already exist in the list.
     */
    public void add(Condition toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the entry {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The entry identity of {@code editedPerson} must not be the same as another existing entry in the list.
     */
    public void setExpense(Condition target, Condition editedExpense) {
        requireAllNonNull(target, editedExpense);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EntryNotFoundException();
        }

        if (target.equals(editedExpense) || contains(editedExpense)) {
            internalList.set(index, editedExpense);
        }
    }

    /**
     * Removes the equivalent entry from the list.
     * The entry must exist in the list.
     */
    public void remove(Condition toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    public void setEntries(ConditionList replacement) {
        requireNonNull(replacement);
        internalList.clear();
        internalList.addAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setEntries(List<Condition> entries) {
        requireAllNonNull(entries);
        internalList.clear();
        internalList.addAll(entries);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public List<Condition> getConditions() {
        return internalList;
    }

    @Override
    public Iterator<Condition> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseList // instanceof handles nulls
                && internalList.equals(((ConditionList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
