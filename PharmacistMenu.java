import java.util.*;

public class PharmacistMenu {
    Scanner scanner = new Scanner(System.in);
    public void displayPharmacistMenu(Pharmacist pharmacist){
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
                    pharmacist.viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    pharmacist.updatePrescriptionStatus();
                    break;
                case 3:
                    pharmacist.viewMedicationInventory();
                    break;
                case 4:
                    pharmacist.submitReplenishmentRequest();
                    break;
                case 5:
                    pharmacist.logout();
                    break;
                default:
                    break;
            }
        }while(choice != 5);
    }
}
