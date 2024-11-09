import java.util.*;

public class PharmacistMenu {
    public static void displayPharmacistMenu(Pharmacist pharmacist){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Pharmacist Menu: \n" + //
                                "1 View Appointment Outcome Record \n" + //
                                "2 Update Prescription Status \n" + //
                                "3 View Medication Inventory \n" + //
                                "4 Submit Replenishment Request \n" + //
                                "5 Logout");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    updatePrescriptionStatus();
                    break;
                case 3:
                    viewMedicationInventory();
                    break;
                case 4:
                    submitReplenishmentRequest();
                    break;
                case 5:
                    pharmacist.logout();
                    break;
                default:
                    break;
            }
        }while(choice != 5);
    }
    public static void viewAppointmentOutcomeRecord() {
		// TODO - implement Pharmacist.viewAppointmentOutcomeRecord
		throw new UnsupportedOperationException();
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
