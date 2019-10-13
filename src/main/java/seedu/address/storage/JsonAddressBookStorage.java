package seedu.address.storage;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public abstract class JsonAddressBookStorage implements AddressBookStorage {

    protected static final Logger logger = LogsCenter.getLogger(JsonExpenseListStorage.class);

    protected Path filePath;

    public JsonAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public abstract Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException;

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public abstract void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

    public Path getFilePath() {
        return filePath;
    }

}
