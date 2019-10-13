package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path expenseListFilePath = Paths.get("data" , "expenseList.json");
    private Path wishListFilePath = Paths.get("data" , "wishList.json");


    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setExpenseListFilePath(newUserPrefs.getExpenseListFilePath());
        setWishListFilePath(newUserPrefs.getWishListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getExpenseListFilePath() {
        return expenseListFilePath;
    }

    public Path getWishListFilePath() {
        return wishListFilePath;
    }

    public void setExpenseListFilePath(Path expenseListFilePath) {
        requireNonNull(expenseListFilePath);
        this.expenseListFilePath = expenseListFilePath;
    }

    public void setWishListFilePath(Path expensewishListFilePath) {
        requireNonNull(wishListFilePath);
        this.wishListFilePath = wishListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && expenseListFilePath.equals(o.expenseListFilePath)
                && wishListFilePath.equals(o.wishListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, expenseListFilePath, wishListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : "
                + "expenditure: " + expenseListFilePath
                + "wish list: " + wishListFilePath);
        return sb.toString();
    }

}
