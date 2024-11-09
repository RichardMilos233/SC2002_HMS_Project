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
                    administrator.displayStaffList();
                    break;
                case 2:
                    administrator.viewAppointmentDetails();
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
}
