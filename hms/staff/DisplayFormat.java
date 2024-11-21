package hms.staff;

import hms.users.User;

/**
 * Implements methods for displaying user information in various formatted styles.
 * It can display both with and without the role information.
 */
public class DisplayFormat implements IDisplayHeader{

    /**
     * Displays the header for role
     */
    @Override
    public void displayHeader(){
        System.out.format("ID     Role          Name                      Gender     Age\n");
        System.out.println("---------------------------------------------------------------");
    }

    /**
     * Displays the user's information in a header excluding role.
     */
    @Override
    public void displayHeaderNoRole(){
        System.out.format("ID     Name                      Gender     Age\n");
        System.out.println("-----------------------------------------------");
    }

    /**
     * Displays the user's information in a simplified table format including role.
     * The output includes hospital ID, role, name, gender, and age, formatted for alignment in columns.
     */
    public static void displayUserFormatRole(User u){
        System.out.format("%-6s %-13s %-25s %-10s %-2d\n", u.getHospitalID(), u.getRole(), u.getName(), u.getGender(), u.getAge());
    }

    /**
     * Displays the user's information in a simplified table format excluding role.
     * The output includes hospital ID, name, gender, and age, formatted for alignment in columns.
     */
    public static void displayUserFormat(User u){
        System.out.format("%-6s %-25s %-10s %-2d\n", u.getHospitalID(), u.getName(), u.getGender(), u.getAge());
    }
}
