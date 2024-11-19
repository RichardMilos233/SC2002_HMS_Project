package hms.inventory;

import hms.storage.CSVService;

import java.util.*;
/**
 * Manages the inventory of medications in a healthcare setting. This includes tracking stock levels,
 * adding new medications, updating existing stocks, and handling stock replenishments.
 */
public class Inventory {


	//private int medications;
	// private List<Medication> medications;
   
    /**
     * Constructs an empty Inventory.
     */
	public Inventory() {
        // medications = new ArrayList<>();
    }
    /*public Inventory(int capacity) {
        medications = new Medication[capacity]; // use array list instead
        count = 0;

	 // add a new medication (idk if inventoru will ever be full?)
	 public void addMedication(String name, int initialStock, int stockAlert) {
        if (count < medications.length) {
            medications[count++] = new Medication(name, initialStock, stockAlert);
            System.out.println(name + " added to inventory.");
        } else {
            System.out.println("Inventory is full. Cannot add " + name);
        }
    }*/

    /**
     * Adds a new medication to the inventory. This method updates the medication list in the CSV file.
     *
     * @param medication The medication to add, which includes its name, stock, and alert level.
     */
    public void addMedication(Medication medication) {
        CSVService csvService = new CSVService();
        // Validation Checks
        if (medication.getMedicationName() == null) {
            System.out.println("Medication Name Missing");
        } else {
            String filePath = "csv/Medicine_List.csv";
            // Current List of Medications
            List<List<String>> medicationList = csvService.read(filePath);

            // New Medication Information
            List<String> newMedication = new ArrayList<>();
            newMedication.add(medication.getMedicationName());
            newMedication.add(String.valueOf(medication.getStock()));
            newMedication.add(String.valueOf(medication.getStockAlert()));

            // Add to Medication List
            medicationList.add(newMedication);

            // Save It
            csvService.write(filePath, medicationList);

            System.out.println(medication.getMedicationName() + " has been added");
        }
    }

	 // Replenish stock for a specific medication
     /**
     * Replenishes the stock for a specified medication by the given amount.
     *
     * @param name The name of the medication to replenish.
     * @param amount The amount to add to the current stock.
     */
	 public void replenishStock(String name, int amount) {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);

        for (int i = 0; i < medicationList.size(); i++) {
            
            if (i == 0) {
                continue; // Skip Headers
            }
            
            // Find The Medication from the List
            if (medicationList.get(i).get(0).equalsIgnoreCase(name)) {
                // Get Current Stock
                int currentStock = Integer.parseInt(medicationList.get(i).get(1));

                // Add replenish Amount to current Stock
                currentStock = currentStock + amount;

                // Update the list with the correct stock number
                medicationList.get(i).set(1, String.valueOf(currentStock));
                if (medicationList.get(i).size() > 3) {
                    medicationList.get(i).remove(3);
                }

                if (amount == 0) {
                    System.out.println("Replenish Request Declined");
                } else {
                    System.out.println("Successfully Accepted Replenishment for " + name);
                }
                
                break;
            }

        }

        // Save It!
        csvService.write(filePath, medicationList);
    }

	 // Consume stock for a specific medication
     // Returns true if successfully consumed stock, if false means no
     /**
     * Consumes a specified amount of stock for a given medication.
     *
     * @param medicationName The name of the medication.
     * @param amountToConsume The amount of stock to be consumed.
     * @return true if the stock was successfully consumed, false if the stock level was insufficient.
     */
	 public boolean consumeStock(String medicationName, int amountToConsume) {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";
        List<List<String>> medicationList = csvService.read(filePath);

        System.out.println(medicationName);
        System.out.println(amountToConsume);
        
        for (int i = 0; i < medicationList.size(); i++) {
            if (i == 0) continue; // Skip Headers

            if (medicationList.get(i).get(0).equalsIgnoreCase(medicationName)) {
                // Check Current Stock if can Consume
                int currentStock = Integer.parseInt(medicationList.get(i).get(1));
                if (currentStock < amountToConsume) {
                    // Cannot consume more than stock level
                    System.out.println("Cannot consume more than stock level!");
                    return false;
                } else {
                    medicationList.get(i).set(1, String.valueOf((currentStock - amountToConsume)));
                    csvService.write(filePath, medicationList);
                    System.out.println("Successfully Dispensed " + medicationName);
                    return true;
                }
                
            }
        }

        System.out.println("Medication not found."); 
        return false;
    }

    /**
     * Prints the current inventory to the standard output, listing all medications and their stock levels.
     */
	public void viewInventory(int index) {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);

        for (int i = 0; i < medicationList.size(); i++) {
            System.out.println(medicationList.get(i).get(0) +
            " | " + medicationList.get(i).get(1) + " | " + medicationList.get(i).get(2));
        }
    }

    /**
     * Checks which medications need to be restocked based on their current levels and alert thresholds.
     *
     * @return A list of medication names that need restocking.
     */
    public List<String> checkStockLevels() {
        CSVService csvService = new CSVService();
        List<String> medicationsThatNeedToBeRestocked = new ArrayList<>();

        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);

        // Go through medication list 
        for (int i = 0; i < medicationList.size(); i++) {
            if (i == 0) continue; // Headers

            int stockLevel = Integer.parseInt(medicationList.get(i).get(1));
            int alertLevel = Integer.parseInt(medicationList.get(i).get(2));

            // Check if stock level are below alert level
            if (stockLevel < alertLevel) {
                // Add to list of medication that need restocking to be returned
                medicationsThatNeedToBeRestocked.add(medicationList.get(i).get(0));
            }
        }

        return medicationsThatNeedToBeRestocked;
    }
    
    /**
     * Sets a new alert line for a specific medication in the inventory.
     *
     * @param medication The medication object whose alert line is to be updated.
     * @param alertLineAmount The new alert level to set for the medication.
     */
    public void setAlertLine(Medication medication, int alertLineAmount) {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);

        // Go through medication list 
        for (int i = 0; i < medicationList.size(); i++) {
            if (i == 0) continue; // Headers

            if (medication.getMedicationName().equals(medicationList.get(i).get(0))) {
                medicationList.get(i).set(2, String.valueOf(alertLineAmount));
                csvService.write(filePath, medicationList);
                return;
            }
        }
    }

    /**
     * Retrieves a list of all medications currently in the inventory along with their stock levels and alert levels.
     *
     * @return A list of all medications in the inventory.
     */
    public List<Medication> retrieveMedications() {
        CSVService csvService = new CSVService();
        List<Medication> medications = new ArrayList<>();

        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);

         // Go through medication list 
         for (int i = 0; i < medicationList.size(); i++) {
            if (i == 0) continue; // Headers

            Medication medication = new Medication();
            medication.setMedicationName(medicationList.get(i).get(0));
            medication.setStock(Integer.parseInt(medicationList.get(i).get(1)));
            medication.setStockAlert(Integer.parseInt(medicationList.get(i).get(2)));

            medications.add(medication);
        }
        
        return medications;
    }

    /**
     * Removes a specified medication from the inventory.
     *
     * @param medicationToRemove The medication object to be removed from the inventory.
     */
    public void removeMedication(Medication medicationToRemove) {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);

        // Go through medication list 
        for (int i = 0; i < medicationList.size(); i++) {
            if (i == 0) continue; // Headers

            if (medicationToRemove.getMedicationName().equals(medicationList.get(i).get(0))) {
                medicationList.remove(i);
                csvService.write(filePath, medicationList);
                System.out.println(medicationToRemove.getMedicationName() + " have been removed");
                return;
            }
        }
    }

    /**
     * Updates the stock level for a specified medication in the inventory.
     *
     * @param name The name of the medication whose stock level is to be updated.
     * @param amountToUpdateTo The new stock amount to set for the medication.
     */
    public void updateStockLevel(String name, int amountToUpdateTo) {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);
        
        for (int i = 0; i < medicationList.size(); i++) {
            if (i == 0) continue; // Skip Headers

            // Find The Medication from the List
            if (medicationList.get(i).get(0).equals(name)) {
                // Update the list with the correct stock number
                medicationList.get(i).set(1, String.valueOf(amountToUpdateTo));

                // Save It!
                csvService.write(filePath, medicationList);
                System.out.println("Successfully Updated " + name);
                return;
            }
        }

        System.out.println("Medication not found."); 
    }
// name changd from string to med
    /**
     * Submits a replenishment request for a specific medication, adding the request amount to the pending replenish queue.
     *
     * @param name The medication for which the replenish request is made.
     * @param amountToReplenish The amount of stock requested to replenish.
     */

    public void replenishRequest(Medication name, int amountToReplenish) {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);

        for (int i = 0; i < medicationList.size(); i++) {
            if (i == 0) continue; // Skip Headers

            // Find The Medication from the List
            //if (medicationList.get(i).get(0).equals(name)) 
                // Update the list with the correct stock number
            if (name.getMedicationName().equalsIgnoreCase(medicationList.get(i).get(0))) {    
                if (medicationList.get(i).size()>3){
                    medicationList.get(i).remove(3);

                } 
                medicationList.get(i).add(String.valueOf(amountToReplenish));
                // Save It!
                csvService.write(filePath, medicationList);
                System.out.println("Successfully Requested Replenishment for " + name);
                

                return;
            }
        }

        System.out.println("Medication not found."); 
    }

    /**
     * Displays options for replenish requests.
     *
     * @param replenishRequest A list of strings representing replenish requests to display.
     */
    public void viewReplenishRequestOptions(List<String> replenishRequest) {
        if (replenishRequest.isEmpty()) {
            System.out.println("No Replenish Requests");
        } else {
            System.out.println("Enter Choice: ");
        }

    }

    /**
     * Retrieves a list of medications that have pending replenish requests along with the requested amounts.
     *
     * @return A list of medications needing replenishment.
     */
    public List<Medication> viewReplenishRequests() {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);

        List<Medication> medicationsForApproval = new ArrayList<>();
        int j = 1;

        for (int i = 0; i < medicationList.size(); i++) {
            if (i == 0) continue; // Skip Headers

            // Find The Medication from the List
            if (medicationList.get(i).size() > 3) {
                Medication medicationsWithReplenishRequests = new Medication();
                medicationsWithReplenishRequests.setMedicationName(medicationList.get(i).get(0));
                medicationsWithReplenishRequests.setStock(Integer.parseInt(medicationList.get(i).get(1)));
                medicationsWithReplenishRequests.setStockAlert(Integer.parseInt(medicationList.get(i).get(2)));
                medicationsWithReplenishRequests.setReplenishAmount(Integer.parseInt(medicationList.get(i).get(3)));
                medicationsForApproval.add(medicationsWithReplenishRequests);
                System.out.println( "("+(j++)+") " + medicationsWithReplenishRequests.getMedicationName() + ", Request to Replenish: " + medicationsWithReplenishRequests.getReplenishAmount());
            }
        }

        return medicationsForApproval;
    }

    /**
     * Approves a replenish request for a specific medication, updating the inventory with the requested stock amount.
     *
     * @param medicationToReplenish The name of the medication to replenish.
     */
    public void approveReplenishRequest(String medicationToReplenish) {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);

        for (int i = 0; i < medicationList.size(); i++) {
            if (i == 0) continue; // Skip Headers

            // Find The Medication from the List
            if (medicationList.get(i).get(0).equals(medicationToReplenish)) {
                // Save It!
                replenishStock(medicationToReplenish, Integer.parseInt(medicationList.get(i).get(3)));
                return;
            }
        }

        System.out.println("Medication not found."); 

    }
}
