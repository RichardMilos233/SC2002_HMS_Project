import java.util.*;

public class Inventory implements IInventory {


	//private int medications;
	// private List<Medication> medications;
   
    
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


	public void viewInventory(int index) {
        CSVService csvService = new CSVService();
        String filePath = "csv/Medicine_List.csv";

        // Get Medication List
        List<List<String>> medicationList = csvService.read(filePath);
        if (index==0){
            System.out.printf("%-25s %-15s %-15s\n", medicationList.get(0).get(0), medicationList.get(0).get(1), medicationList.get(0).get(2));
            for (int i = 1; i < medicationList.size(); i++) {
                System.out.printf("%-25s %-15s %-15s\n", medicationList.get(i).get(0), medicationList.get(i).get(1), medicationList.get(i).get(2));
            }
        } else{
            System.out.printf("(  ) %-25s %-15s %-15s\n", medicationList.get(0).get(0), medicationList.get(0).get(1), medicationList.get(0).get(2));
            for (int i = 1; i < medicationList.size(); i++) {
                System.out.printf("(%2s) %-25s %-15s %-15s\n", i, medicationList.get(i).get(0), medicationList.get(i).get(1), medicationList.get(i).get(2));
            }
        }
        
    }

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

    public void viewReplenishRequestOptions(List<String> replenishRequest) {
        if (replenishRequest.isEmpty()) {
            System.out.println("No Replenish Requests");
        } else {
            System.out.println("Enter Choice: ");
        }

    }

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


