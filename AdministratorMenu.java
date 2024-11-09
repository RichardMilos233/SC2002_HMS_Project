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
                                "4 View Replenishment Requests \n" + //
                                "5 Logout ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    displayStaffList();
                    break;
                case 2:
                    viewAppointmentDetails();
                    break;
                case 3:
                    displayInventory();
                    break;
                case 4:
                    Inventory inventoryService = new Inventory();
                    List<Medication> replenishRequests = inventoryService.viewReplenishRequests();
                    if (replenishRequests.isEmpty()) {
                        System.out.println("No Replenish Requests");
                    }
                    else {
                        int medicationChoice = Integer.parseInt(scanner.nextLine());
                        if (medicationChoice > replenishRequests.size()) {
                            System.out.println("Invalid Choice");
                            break;
                        } else {
                            System.out.println("1 Approve");
                            System.out.println("2 Decline");
                            int approveOrDeclineChoice = Integer.parseInt(scanner.nextLine());
                            int amountToReplenish = 0; // Default 0 to replenish if decline
                            if (approveOrDeclineChoice == 1) {
                                amountToReplenish = replenishRequests.get(medicationChoice-1).getReplenishAmount();
                            }
                            inventoryService.replenishStock(replenishRequests.get(medicationChoice-1).getMedicationName(), amountToReplenish);
                        }
                    }
                    
                    // inventoryService.approveReplenishRequest(replenishRequests.get(Integer.parseInt(scanner.nextLine())-1));
                    break;
                case 5:
                    administrator.logout();
                    break;
                default:
                    break;
            }
        }while (choice != 5);
    }
  
    private static void displayInventory() { // this moved to somewhere else
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        Inventory inventoryService = new Inventory();

        do {
            System.out.println("Inventory Management: \n" + //
                                "1 Display Medications \n" + //
                                "2 Add New Medication \n" + //
                                "3 Remove Medication \n" + //
                                "4 Update Stock Levels \n" + //
                                "5 Exit ");
                                
            List<Medication> medications = inventoryService.retrieveMedications();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inventoryService.viewInventory();
                    break;
                case 2:
                    Medication newMedication = new Medication();
                    System.out.println("Enter Medication Name: ");
                    newMedication.setMedicationName(scanner.nextLine());
                    System.out.println("Enter Stock Level: ");
                    newMedication.setStock(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Enter Stock Alert Level: ");
                    newMedication.setStockAlert(Integer.parseInt(scanner.nextLine()));

                    inventoryService.addMedication(newMedication); // can wrap this into another method?
                    break;
                case 3:

                    for (int i = 0; i < medications.size(); i++) {
                        System.out.println( (i+1) + " - " + medications.get(i).getMedicationName());
                    }

                    System.out.println("Choose Medication to Remove: ");
                    int medChoice = Integer.parseInt(scanner.nextLine());
                    
                    inventoryService.removeMedication(medications.get(medChoice-1));
                    break;
                case 4:
                    inventoryService.viewInventory();
                    System.out.println("Enter Medication Name to Update Stock Levels: ");
                    String medToRemove = scanner.nextLine();
                    System.out.println("Enter Stock Level to Set to: ");
                    int stockLevelToSet = Integer.parseInt(scanner.nextLine());
                    inventoryService.updateStockLevel(medToRemove, stockLevelToSet);
                    break;
                default:
                    break;
            }
        } while (choice != 5);
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
                System.out.format("ID     Name                 Gender Age\n");
                System.out.println("-------------------------------------------");
                StaffService.displayAdminList(1);
                break; 
            case 3:
                System.out.format("ID     Name                 Gender Age\n");
                System.out.println("-------------------------------------------");
                StaffService.displayDoctorList(1);
                break;
            case 4:
                System.out.format("ID     Name                 Gender Age\n");
                System.out.println("-------------------------------------------");
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
        Scanner scanner = new Scanner(System.in);
        String name, defaultPass;
        int role, gender, age;
		// TODO - call StaffService.addStaff(name, egegegeg)
        do { 
            System.out.println("Staff's Name: ");
            name = scanner.next();
        } while(!name.isBlank() && !name.isEmpty() && (name.length()-name.trim().length() < 6));
        name.substring(0, 1).toUpperCase();
        int[] spaceIndex = new int[5];
        for (int i = 0; i<name.length(); i++){
            
        }

        System.out.println("Staff's Role: ");
        System.out.println("Staff's Gender");
        System.out.println("Staff's Age");
        System.out.println("Make a default password for the new Staff member: ");
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
