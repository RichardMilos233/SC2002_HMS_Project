package hms.utils.billing;

public class AmoxicillinFee implements MedicationFee{
    // Constant price per unit
    private static final double PRICE_PER_UNIT = 2.50;
    private static final double TAX_RATE = 0.1;

    // Implement the calculateFee method
    @Override
    public double calculateFee(int quantity) {
        return quantity * PRICE_PER_UNIT * (1+TAX_RATE);
    }
}