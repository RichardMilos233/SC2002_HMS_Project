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
        // 0 is normal, 1 is Administrator, 2 is doctor, 3 is pharmacist
        Scanner scanner = new Scanner(System.in);
        int c = 0;
        do {  // inconsistent print method what is the preference
            System.out.println(""" 
                               1 Display All Staff 
                               2 Display Administrators 
                               3 Display Doctors 
                               4 Display Pharmacists 
                               """);
            c = scanner.nextInt();
        } while (c>4 || c<1);
        switch (c){
            case 1:
                StaffService.displayStaffList();
                break;
            case 2:
                StaffService.displayAdminList(0);
                break; 
            case 3:
                StaffService.displayDoctorList(0);
                break;
            case 4:
                StaffService.displayPharmacistList(0);
                break;
            default:
                displayStaffList();
        }


        // ask how they want to manage the staff in a loop and then call add update remove etc
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
