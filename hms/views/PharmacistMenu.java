package hms.views;

import hms.account.Pharmacist;
import hms.appointment.PharmacistAppointmentOutcomeRecordViewer;
import hms.inventory.Inventory;
import hms.inventory.Medication;
import hms.utils.Validator;
import java.util.List;
import java.util.Scanner;
/**
 * Provides a user interface menu specifically for pharmacists to manage their daily tasks and interactions within
 * the healthcare system. This includes managing prescriptions, viewing medication inventories, and handling
 * replenishment requests.
 */
public class PharmacistMenu {
    /**
     * Displays a menu for pharmacists, allowing them to select and perform various operations related to their role.
     * This method continuously displays options until the pharmacist decides to logout.
     *
     * @param pharmacist The pharmacist who is interacting with the system.
     */
    public static void displayPharmacistMenu(Pharmacist pharmacist){
        Scanner scanner = new Scanner(System.in);
        Inventory inventoryService = new Inventory();
        int choice = 0;
        do{

            System.out.println("\n-------------Pharmacist Menu-------------\n" + //
                                "1 View Appointment Outcome Record \n" + //
                                "2 Update Prescription Status \n" + //
                                "3 View Medication Inventory \n" + //
                                "4 Submit Replenishment Request \n" + //
                                "5 View Pending Replenishment Requests \n" + //
                                "6 Change Password \n" + //
                                "7 Logout");
            List<Medication> medications = inventoryService.retrieveMedications();

            choice = Validator.validateInt(scanner);
            switch (choice) {
                case 1:
                    viewAppointmentOutcomeRecord();
                    //call from appointment outcome recorder
                    break;
                case 2:
                    updatePrescriptionStatus();
                    // make: false for not prescribed, true for prescribed 
                    break;
                case 3:
                    inventoryService.viewInventory();
                    //call from inventory, return types n stock of each medication 
                    break;
                case 4:
                    inventoryService.viewInventory();
                    // for (int i = 0; i < medications.size(); i++) {
                    //     System.out.println( (i+1) + " - " + medications.get(i).getMedicationName());
                    // }
                    sendReplenishRequest(medications, inventoryService, scanner);
                    //submit to inventory -> calls medication
                    break;
                case 5:
                    inventoryService.viewReplenishRequests();
                    break;

                case 6:
                    pharmacist.changePassword();
                    break;
                case 7:
                    pharmacist.logout();
                    return;
                    
                default:
                    break;
            }
        }while(choice != 7);
    }

    /**
     * A method to view appointment outcomes, specifically called within the pharmacist menu.
     */
    public static void viewAppointmentOutcomeRecord() {
        PharmacistAppointmentOutcomeRecordViewer.displayAppointmentOutcomes(0);
	}

	public static void updatePrescriptionStatus() {
        PharmacistAppointmentOutcomeRecordViewer.displayAppointmentOutcomes(1);
		
	}

    public static void sendReplenishRequest(List<Medication> medications, Inventory inventoryService, Scanner scanner){
        int medToReplenish;
        do {
            System.out.println("Select Medication to Replenish OR 0 to Return: ");
            medToReplenish = Validator.validateInt(scanner);
            if (medToReplenish == 0){
                return;
            }
        } while (medToReplenish<0 || medToReplenish>medications.size());
        
        int stockLevelToSet;
        do { 
            System.out.println("Enter Amount of Stock to Add OR 0 to Return: ");
            stockLevelToSet = Validator.validateInt(scanner);
            if (stockLevelToSet == 0){
                return;
            }
        } while (stockLevelToSet<0);
        inventoryService.replenishRequest(medications.get(medToReplenish-1), stockLevelToSet);
    }

	// public static void viewMedicationInventory() {
	// 	// TODO - implement Pharmacist.viewMedicationInventory
	// 	throw new UnsupportedOperationException();
	// }

	// public static void submitReplenishmentRequest() {
	// 	// TODO - implement Pharmacist.submitReplenishmentRequest
	// 	throw new UnsupportedOperationException();
	// }
}

