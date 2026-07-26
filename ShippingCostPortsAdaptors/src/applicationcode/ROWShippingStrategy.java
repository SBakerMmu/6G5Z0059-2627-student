package applicationcode;

class ROWShippingStrategy implements ShippingCostStrategy {
    private final double minCharge;
    private final double costPerKg;

    ROWShippingStrategy(double minCharge, double costPerKg) {
        this.minCharge = minCharge;
        this.costPerKg = costPerKg;
    }

    @Override
    public double calculate(double weight) {
        return Math.max(minCharge, weight * costPerKg);
    }
}
