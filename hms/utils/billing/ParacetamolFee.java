package hms.utils.billing;

public class ParacetamolFee implements MedicationFee {
    // Constant price per unit
    private static final double PRICE_PER_UNIT = 2.50;

    // Implement the calculateFee method
    @Override
    public double calculateFee(int quantity) {
        return quantity * PRICE_PER_UNIT;
    }
}
