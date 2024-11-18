import java.util.List;
import java.util.Scanner;

public class PharmacistMenu {
    public static void displayPharmacistMenu(Pharmacist pharmacist){
        Scanner scanner = new Scanner(System.in);
        Inventory inventoryService = new Inventory();
        int choice = 0;
        do{

            System.out.println("Pharmacist Menu: \n" + //
                                "1 View Appointment Outcome Record \n" + //
                                "2 Update Prescription Status \n" + //
                                "3 View Medication Inventory \n" + //
                                "4 Submit Replenishment Request \n" + //
                                "5 Logout");
            List<Medication> medications = inventoryService.retrieveMedications();                    

            choice = Integer.parseInt(scanner.nextLine());
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
                    for (int i = 0; i < medications.size(); i++) {
                        System.out.println( (i+1) + " - " + medications.get(i).getMedicationName());
                    }
                    System.out.println("Choose which medication to Replenish: ");
                    //String medToReplenish = scanner.nextLine();
                    int medToReplenish = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter Stock Level: ");
                    int stockLevelToSet = Integer.parseInt(scanner.nextLine());
                    inventoryService.replenishRequest(medications.get(medToReplenish-1), stockLevelToSet);
                    //submit to inventory -> calls medication
                    break;
                case 5:
                    pharmacist.changePassword();
                    break;
                case 6:
                    pharmacist.logout();
                    break;
                    
                default:
                    break;
            }
        }while(choice != 6);
    }
    public static void viewAppointmentOutcomeRecord() {
        PharmacistAppointmentOutcomeRecordViewer.getAppointmentOutcomes();
	}

	public static void updatePrescriptionStatus() {
		// TODO - implement Pharmacist.updatePrescriptionStatus
		throw new UnsupportedOperationException();
	}

	public static void viewMedicationInventory() {
		// TODO - implement Pharmacist.viewMedicationInventory
		throw new UnsupportedOperationException();
	}

	public static void submitReplenishmentRequest() {
		// TODO - implement Pharmacist.submitReplenishmentRequest
		throw new UnsupportedOperationException();
	}
}

