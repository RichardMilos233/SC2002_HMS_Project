import java.util.List;

public interface IInventory {
    void addMedication(Medication medication);
    void replenishStock(String name, int amount);
    void consumeStock(String name, int amount);
    void viewInventory();
    List<String> checkStockLevels(); // this will check the stock level and return all medication that need stocking
    void setAlertLine(Medication medication, int alertLineAmount);
    void removeMedication(Medication medicationToRemove);
    void updateStockLevel(String name, int amountToUpdateTo);
    void replenishRequest(String name, int amountToReplenish);
    List<Medication> viewReplenishRequests();
    void approveReplenishRequest(String medicationToReplenish);
    void viewReplenishRequestOptions(List<String> replenishRequest);
}
