import java.util.*;

public class AdministratorMenu {
    public static void displayAdminMenu(Administrator administrator){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Administrator Menu: \n" + //
                                "1 View and Manage Hospital Staff \n" + //
                                "2 View Appointments details \n" + //
                                "3 View and Manage Medication Inventory \n" + //
                                "4 Approve Replenishment Requests \n" + //
                                "5 Logout ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    administrator.displayStaffList();
                    break;
                case 2:
                    administrator.viewAppointmentDetails();
                    break;
                case 3:
                    administrator.viewInventory();
                    break;
                case 4:
                    administrator.approveReplenishmentRequest();
                    break;
                case 5:
                    administrator.logout();
                    break;
                    
                default:
                    break;
            }
        }while (choice != 5);
    }
}
