package hms.inventory;

import hms.utils.Validator;
import java.util.List;
import java.util.Scanner;
/**
 * Provides functionalities for managing the medication inventory by an administrator.
 * This class includes methods to handle replenish requests for medications within the inventory.
 */
public class AdministratorInventoryManagement {
    /**
     * Manages inventory by processing replenish requests for medications.
     * The method lists all current replenish requests. If there are any, it allows the administrator
     * to approve or decline each request. The administrator first selects a medication from the list
     * of requests, then decides whether to approve or decline the replenish request.
     * If approved, the medication stock is replenished by the requested amount; if declined,
     * no replenishment occurs.
     */
    public static void manageInventory() {
        Scanner scanner = new Scanner(System.in);
        Inventory inventoryService = new Inventory();
        List<Medication> replenishRequests = inventoryService.viewReplenishRequests();
        if (replenishRequests.isEmpty()) {
            System.out.println("No Replenish Requests");
        }
        else {
            int medicationChoice;
            do { 
                System.out.println("Select a Medicine to Approve or Deny OR 0 to Return: ");
                medicationChoice = Validator.validateInt(scanner);
                if (medicationChoice == 0){
                    return;
                }
            } while (medicationChoice > replenishRequests.size());
            System.out.println("1 Approve");
            System.out.println("2 Decline");
            int approveOrDeclineChoice = Validator.validateInt(scanner);
            int amountToReplenish = 0; // Default 0 to replenish if decline
            if (approveOrDeclineChoice == 1) {
                amountToReplenish = replenishRequests.get(medicationChoice-1).getReplenishAmount();
            }
            inventoryService.replenishStock(replenishRequests.get(medicationChoice-1).getMedicationName(), amountToReplenish);
        
        }            
    }
}
