/**
 * Represents a medication including its name, current stock, stock alert level, and replenishment details.
 * This class provides methods for managing stock levels and checking stock against alert thresholds.
 */
public class Medication {

	private String medicationName;
	private int stock;
	private int stockAlert;
    private int replenishAmount;

    /**
     * Constructs a new Medication instance with specified details.
     * @param medicationName The name of the medication.
     * @param initialStock Initial stock level of the medication.
     * @param stockAlert The stock level at which an alert is raised.
     * @param replenishAmount The amount by which stock is replenished.
     */
	public Medication(String medicationName, int initialStock, int stockAlert, int replenishAmount) {
        this.medicationName = medicationName;
        this.stock = initialStock;
        this.stockAlert = stockAlert;
        this.replenishAmount = replenishAmount;
    }

     /**
     * Default constructor for creating a Medication instance without preset values.
     */
    public Medication() {}

    /**
     * Sets the stock alert level for the medication.
     * @param newStockAlert The new alert level at which a replenishment alert will be triggered.
     */
	public void setStockAlert(int newStockAlert) {
		this.stockAlert = newStockAlert;
	}

    // ignore all mtds under this pls... pang chance
    /**
     * Consumes a specified amount of this medication's stock.
     * @param amount The amount of medication to consume.
     */
	public void consumeStock(int amount) {
        if (amount <= stock) {
            stock -= amount;
            System.out.println(amount + " units of " + medicationName + " consumed. Remaining stock: " + stock);
        } else {
            System.out.println("Insufficient stock of " + medicationName + " to consume " + amount + " units.");
        }
    }

    /**
     * Replenishes the stock of this medication by a specified amount.
     * @param amount The amount by which to increase the stock.
     */
	public void replenishStock(int amount) {
        if (amount > 0) {
            stock += amount;
            System.out.println(amount + " units of " + medicationName + " replenished. Current stock: " + stock);
        } else {
            System.out.println("Replenishment amount must be positive.");
        }
    }

    /**
     * Checks if the current stock is below the alert level.
     * @return true if the current stock is less than the alert level, false otherwise.
     */
	public boolean isBelowAlertLevel() {
        return stock < stockAlert;
    }

    /**
     * Gets the name of the medication.
     * @return The name of the medication.
     */
    public String getMedicationName() {
        return medicationName;
    }

    /**
     * Gets the current stock level of the medication.
     * @return The current stock level of the medication.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Gets the alert level of the stock for the medication.
     * @return The stock alert level at which a replenishment alert will be triggered.
     */
    public int getStockAlert() {
        return stockAlert;
    }

    /**
     * Returns a string representation of this medication.
     * @return A formatted string with the medication's name, stock, and alert level.
     */
    public String toString() {
        return String.format("%s: %d units (Alert Level: %d)", medicationName, stock, stockAlert);
    }

    /**
     * Sets the name of the medication.
     * @param medicationName The new name of the medication.
     */
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    /**
     * Sets the stock level of the medication.
     * @param stock The new stock level of the medication.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the replenish amount for the medication.
     * @return The amount to replenish when the stock falls below the alert level.
     */
    public int getReplenishAmount() {
        return replenishAmount;
    }

    /**
     * Sets the replenish amount for the medication.
     * @param replenishAmount The new amount to replenish when the stock falls below the alert level.
     */
    public void setReplenishAmount(int replenishAmount) {
        this.replenishAmount = replenishAmount;
    }
}