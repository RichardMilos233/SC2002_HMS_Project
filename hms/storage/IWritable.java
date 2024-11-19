package hms.storage;

import java.util.*;
/**
 * The IWritable interface defines a method for writing data to a file. This interface is intended to abstract
 * the details of file writing operations, allowing for different implementations that can handle various types
 * of data sources or file formats.
 */
public interface IWritable {
    /**
     * Writes data to a specified file. The data is provided as a list of lists, where each inner list represents
     * a line or record that should be written to the file.
     *
     * @param filePath The path to the file where data is to be written. If the file does not exist, it should be created.
     *                 If it does exist, this method may either overwrite it or append to it, depending on the implementation.
     * @param data A List of Lists of Strings, where each inner List contains the elements of a single line or record
     *             that should be written to the file. How these elements are formatted and written to the file depends
     *             on the implementation specifics.
     */
    void write(String filePath, List<List<String>> data);
}
