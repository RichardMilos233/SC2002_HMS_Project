package hms.staff;

import java.util.Comparator;

import hms.users.User;

/**
 * Interface defining methods for displaying information in different formats.
 */
public interface IDisplay {
     /**
     * Displays information in a default format.
     */
    void displayByRole();

    /**
     * Displays information sorted by a specific attribute.
     *
     * @param comparator The comparator to use for sorting.
     */
    void displayByAttribute(Comparator<User> comparator);
}
