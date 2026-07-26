package applicationcode.domainmodel;

class EURShippingStrategy implements ShippingCostStrategy {
    private final double costPerKg;

    EURShippingStrategy(double costPerKg) {
        this.costPerKg = costPerKg;
    }

    @Override
    public double calculate(double weight) {
        return weight * costPerKg;
    }
}
