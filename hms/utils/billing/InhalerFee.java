package hms.utils.billing;

public class InhalerFee implements MedicationFee{
    // Constant price per unit
    private static final double PRICE_PER_UNIT = 19.0;

    @Override
    public double calculateFee(int quantity) {
        return quantity * PRICE_PER_UNIT;
    }
}
