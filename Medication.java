public class Medication {

	private String medicationName;
	private int stock;
	private int stockAlert;
    private int replenishAmount;



	public Medication(String medicationName, int initialStock, int stockAlert, int replenishAmount) {
        this.medicationName = medicationName;
        this.stock = initialStock;
        this.stockAlert = stockAlert;
        this.replenishAmount = replenishAmount;
    }

    public Medication() {
        
    }

	/**
	 * 
	 * @param newStockAlert
	 */
	public void setStockAlert(int newStockAlert) {
		this.stockAlert = newStockAlert;
	}

    // ignore all mtds under this pls... pang chance
	public void consumeStock(int amount) {
        if (amount <= stock) {
            stock -= amount;
            System.out.println(amount + " units of " + medicationName + " consumed. Remaining stock: " + stock);
        } else {
            System.out.println("Insufficient stock of " + medicationName + " to consume " + amount + " units.");
        }
    }

	public void replenishStock(int amount) {
        if (amount > 0) {
            stock += amount;
            System.out.println(amount + " units of " + medicationName + " replenished. Current stock: " + stock);
        } else {
            System.out.println("Replenishment amount must be positive.");
        }
    }

	public boolean isBelowAlertLevel() {
        return stock < stockAlert;
    }

    // getters for medication name and stock
    public String getMedicationName() {
        return medicationName;
    }

    
    public int getStock() {
        return stock;
    }

    public int getStockAlert() {
        return stockAlert;
    }

    


    public String toString() {
        return String.format("%s: %d units (Alert Level: %d)", medicationName, stock, stockAlert);
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getReplenishAmount() {
        return replenishAmount;
    }

    public void setReplenishAmount(int replenishAmount) {
        this.replenishAmount = replenishAmount;
    }

    
}
