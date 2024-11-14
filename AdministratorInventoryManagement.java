import java.util.List;
import java.util.Scanner;

public class AdministratorInventoryManagement {
    public static void manageInventory() {
        Scanner scanner = new Scanner(System.in);
        Inventory inventoryService = new Inventory();
        List<Medication> replenishRequests = inventoryService.viewReplenishRequests();
        if (replenishRequests.isEmpty()) {
            System.out.println("No Replenish Requests");
        }
        else {
            int medicationChoice = Integer.parseInt(scanner.nextLine());
            if (medicationChoice > replenishRequests.size()) {
                System.out.println("Invalid Choice");
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
    }
}
