package hms.account;

import hms.account.users.User;
import java.util.Comparator;

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
