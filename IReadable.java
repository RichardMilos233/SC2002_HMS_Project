import java.util.*;
/**
 * The IReadable interface defines a method for reading data from a file and returning it as a list of lists.
 * Each inner list represents a line or record from the file, where each entry in the list corresponds to an element
 * or field in that line. This interface is intended to abstract the details of file reading and to allow for
 * different implementations that can handle various types of data sources or file formats.
 */
public interface IReadable {
    /**
     * Reads data from a specified file and returns it organized as a list of lists.
     * Each inner list represents a single line or record from the file, parsed into its constituent elements.
     *
     * @param filePath The path to the file that is to be read. The file should exist and be accessible.
     * @return A List of Lists of Strings, where each inner List contains the elements of a single line or record
     *         in the file. The structure and content of the returned list depend on the implementation and the
     *         nature of the file being read.
     */
    List<List<String>> read(String filePath);
}
