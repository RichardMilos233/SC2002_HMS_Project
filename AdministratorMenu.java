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
                                "5 Logout");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayStaffList();
                    break;
                case 2:
                    viewAppointmentDetails();
                    break;
                case 3:
                    viewInventory();
                    break;
                case 4:
                    approveReplenishmentRequest();
                    break;
                case 5:
                    administrator.logout();
                    break;
                    
                default:
                    break;
            }
        }while (choice != 5);
    }

    public static void displayStaffList() {
        Scanner scanner = new Scanner(System.in);
        int c = 0;
        do {  // inconsistent print method what is the preference
            System.out.println(""" 
                                 Would you like to:
                               1 Display All Staff
                               2 Display Administrators 
                               3 Display Doctors 
                               4 Display Pharmacists
                               5 Display All Staff Alphabetically 
                               """);
            c = scanner.nextInt();
        } while (c>5 || c<1);
        switch (c){
            case 1:
                StaffService.displayStaffList(1);
                // TO DO - add option for alphabetical
                break;
            case 2:
                StaffService.displayAdminList(1);
                break; 
            case 3:
                StaffService.displayDoctorList(1);
                break;
            case 4:
                StaffService.displayPharmacistList(1);
                break;
            case 5:
                StaffService.displayStaffList(0);
                break;
            default:
                displayStaffList();
        }
        c = 0;
        System.out.println("""
                             Would you like to: 
                           1 Return to Menu  
                           2 Add Staff 
                           3 Update Staff 
                           4 Remove Staff""");
        c = scanner.nextInt();
        switch (c){
            case 1:
                return;
            case 2:
                // name, role, id automatically allocated i think, gender, age, salary?
                StaffService.addStaff();
                break; 
            case 3:
                // which one 
                StaffService.updateStaff();;
                break;
            case 4:
                // which one 
                StaffService.removeStaff();
                break;
            default:
                return;
        }

		// TODO - call StaffService.displaystafflist 
		throw new UnsupportedOperationException();
	}

	public void addStaff() {
		// TODO - call StaffService.addStaff(name, egegegeg)
		throw new UnsupportedOperationException();
	}

	public void updateStaff() {
		// TODO - call StaffService.updateStaff(name to be updated, egeg)
		throw new UnsupportedOperationException();
	}

	public void removeStaff() {
		// TODO - call StaffService.removeStaff(who to remove egegeg)
		throw new UnsupportedOperationException();
	}

	public static void viewAppointmentDetails() {
		// TODO - implement Administrator.viewAppointmentDetails
		throw new UnsupportedOperationException();
	}

	public static void viewInventory() {
		// TODO - implement Administrator.viewInventory
		throw new UnsupportedOperationException();
	}

	public void addStock() {
		// TODO - implement Administrator.addStock
		throw new UnsupportedOperationException();
	}

	public void removeStock() {
		// TODO - implement Administrator.removeStock
		throw new UnsupportedOperationException();
	}

	public void updateStock() {
		// TODO - implement Administrator.updateStock
		throw new UnsupportedOperationException();
	}

	public void updateStockAlert() {
		// TODO - implement Administrator.updateStockAlert
		throw new UnsupportedOperationException();
	}

	public static void approveReplenishmentRequest() {
		// TODO - implement Administrator.approveReplenishmentRequest
		throw new UnsupportedOperationException();
	}
}
