package applicationcode.usecase.calculateshipping;

public class ShippingCost {
    private final double minCharge;
    private final double costPerKg;

    public ShippingCost(double minCharge, double costPerKg) {
        this.minCharge = minCharge;
        this.costPerKg = costPerKg;
    }

    public double getMinCharge() {
        return minCharge;
    }
    public double getCostPerKg() {
        return costPerKg;
    }
}

