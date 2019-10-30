package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.AutoExpense;
import seedu.address.model.person.Budget;
import seedu.address.model.person.Category;
import seedu.address.model.person.CategoryList;
import seedu.address.model.person.Entry;
import seedu.address.model.person.Expense;
import seedu.address.model.person.ExpenseReminder;
import seedu.address.model.person.Income;
import seedu.address.model.person.SortSequence;
import seedu.address.model.person.SortType;
import seedu.address.model.person.Wish;
import seedu.address.model.person.WishReminder;
import seedu.address.model.statistics.StatisticsManager;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Entry> PREDICATE_SHOW_ALL_ENTRIES = unused -> true;
    Predicate<Expense> PREDICATE_SHOW_ALL_EXPENSES = unused -> true;
    Predicate<Income> PREDICATE_SHOW_ALL_INCOMES = unused -> true;
    Predicate<Wish> PREDICATE_SHOW_ALL_WISHES = unused -> true;
    Predicate<Budget> PREDICATE_SHOW_ALL_BUDGETS = unused -> true;
    Predicate<AutoExpense> PREDICATE_SHOW_ALL_AUTOEXPENSES = unused -> true;
    Predicate<ExpenseReminder> PREDICATE_SHOW_ALL_EXPENSE_REMINDERS = unused -> true;

    void setStats(StatisticsManager stats);

    StatisticsManager getStats();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    boolean hasCategory(Category category);

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    boolean hasEntry(Entry entry);

    boolean hasBudget(Budget budget);

    boolean hasWish(Wish wish);

    boolean hasExpenseReminder(ExpenseReminder reminder);

    /**
     * Deletes the given category. The category must exist in the address book.
     */
    void deleteCategory(Category target);

    /**
     * Deletes the given entry. The entry must exist in the address book.
     */
    void deleteEntry(Entry target);

    /**
     * Deletes the given expense. The entry must exist in the address book.
     */
    void deleteExpense(Expense target);

    /**
     * Deletes the given income. The income must exist in the address book.
     */
    void deleteIncome(Income target);

    /**
     * Deletes the given wish. The wish must exist in the address book.
     */
    void deleteWish(Wish target);

    void deleteExpenseReminder(ExpenseReminder target);

    /**
     * Deletes the given budget.
     * The budget must exist in the address book.
     */
    void deleteBudget(Budget target);

    /**
     * Deletes the given AutoExpense. The entry must exist in the address book.
     */
    void deleteAutoExpense(AutoExpense target);

    /**
     * Adds the given person. {@code person} must not already exist in the address
     * book.
     */
    void addEntry(Entry entry);

    void addCategory(Category category);

    void addExpense(Expense expense);

    void addIncome(Income income);

    void addWish(Wish wish);

    void addBudget(Budget budget);

    void addAutoExpense(AutoExpense autoExpense);

    void addExpenseReminder(ExpenseReminder expenseReminder);

    void setCategory(Category target, Category editedCategory);

    /**
     * Replaces the given entry {@code target} with {@code editedEntry}.
     * {@code target} must exist in the address book. The entry identity of
     * {@code editedEntry} must not be the same as another existing entry in the
     * address book.
     */
    void setEntry(Entry target, Entry editedEntry);

    void setIncome(Income target, Income editedEntry);

    void setExpense(Expense target, Expense editedEntry);

    void setWish(Wish target, Wish editedWish);

    void setBudget(Budget target, Budget editedbudget);

    void setExpenseReminder(ExpenseReminder target, ExpenseReminder editedEntry);

    CategoryList getCategoryList();

    /** Returns an unmodifiable view of the income category list */
    ObservableList<Category> getIncomeCategoryList();

    /** Returns an unmodifiable view of the expense category list */
    ObservableList<Category> getExpenseCategoryList();

    /** Returns an unmodifiable view of the filtered entry list */
    ObservableList<Entry> getFilteredEntryList();

    /** Returns an unmodifiable view of the filtered expenditure list */
    ObservableList<Expense> getFilteredExpenses();

    /** Returns an unmodifiable view of the filtered entry list */
    ObservableList<Income> getFilteredIncomes();

    /** Returns an unmodifiable view of filtered expense and income list */
    ObservableList<Entry> getFilteredExpensesAndIncomes();

    /** Returns an unmodifiable view of the filtered entry list */
    ObservableList<Wish> getFilteredWishes();

    /** Returns an unmodifiable view of the filtered budget list */
    ObservableList<Budget> getFilteredBudgets();

    /** Returns an unmodifiable view of the filtered expenditure list */
    ObservableList<AutoExpense> getFilteredAutoExpenses();

    /** Returns an unmodifiable view of the filtered expense reminder list */
    ObservableList<ExpenseReminder> getFilteredExpenseReminders();

    ObservableList<WishReminder> getFilteredWishReminders();
    /**
     * Updates the filter of the filtered entry list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEntryList(Predicate<Entry> predicate);

    void updateFilteredExpenses(Predicate<Expense> predicate);

    void updateFilteredIncomes(Predicate<Income> predicate);

    void updateFilteredWishes(Predicate<Wish> predicate);

    void updateFilteredBudgets(Predicate<Budget> predicate);

    /**
     * Returns true if the model has previous finance tracker states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone finance tracker states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's finance tracker to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's finance tracker to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current finance tracker state for undo/redo
     */
    void commitAddressBook();

    void updateFilteredAutoExpenses(Predicate<AutoExpense> predicate);

    void sortFilteredEntry(SortType comparator, SortSequence sequence);

    void updateFilteredExpenseReminders(Predicate<ExpenseReminder> predicate);

    void updateFilteredWishReminders(Predicate<WishReminder> predicate);
}
