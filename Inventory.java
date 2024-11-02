public class Inventory {

	//private int medications;
	private Medication[] medications;
    private int count;

	public Inventory(int capacity) {
        medications = new Medication[capacity];
        count = 0;
    }

	 // add a new medication (idk if inventoru will ever be full?)
	 public void addMedication(String name, int initialStock, int stockAlert) {
        if (count < medications.length) {
            medications[count++] = new Medication(name, initialStock, stockAlert);
            System.out.println(name + " added to inventory.");
        } else {
            System.out.println("Inventory is full. Cannot add " + name);
        }
    }

	 // Replenish stock for a specific medication
	 public void replenishStock(String name, int amount) {
        for (int i = 0; i < count; i++) {
            if (medications[i].getMedicationName().equals(name)) {
                medications[i].replenishStock(amount); // Call the existing method
                return;
            }
        }
        System.out.println("Medication not found."); 
    }

	 // Consume stock for a specific medication
	 public void consumeStock(String name, int amount) {
        for (int i = 0; i < count; i++) {
            if (medications[i].getMedicationName().equals(name)) {
                medications[i].consumeStock(amount); // Call the existing method
                return;
            }
        }
        System.out.println("Medication not found.");
    }

//make mtd to check stock lvl?


	public void viewInventory() {
        for (int i = 0; i < count; i++) {
            System.out.println(medications[i]);
        }
    }
}
