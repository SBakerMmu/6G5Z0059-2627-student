package applicationcode;

class ShippingCostCalculator implements Provided {
    private final Required required;

    public ShippingCostCalculator(Required required) {
        this.required = required;
    }

    @Override
    public double calculate(Region region, double weight) {
        return getStrategy(region).calculate(weight);
    }


    ShippingCostStrategy getStrategy(Region region) {
        ShippingCost shippingCost = required.getShippingCostForRegion(region);
        return  switch (region) {
            case UK -> new UKShippingStrategy();
            case EUR -> new EURShippingStrategy(shippingCost.getCostPerKg());
            // Note: The ROW strategy uses both min charge and cost per kg
            case ROW -> new ROWShippingStrategy(shippingCost.getMinCharge(), shippingCost.getCostPerKg());
            default -> throw new IllegalArgumentException("Unknown region: " + region);
        };
    }

    //Example of calculation implementation without using Strategy pattern
    double calculateWithoutStrategy(Region region, double weight) {
        ShippingCost shippingCost = required.getShippingCostForRegion(region);
        switch (region) {
            case UK:
            case EUR:
                double costPerKg = shippingCost.getCostPerKg();
                return weight * costPerKg;
            case ROW:
                double rowCostPerKg = shippingCost.getCostPerKg();
                double minCharge = shippingCost.getMinCharge();
                double cost = weight * rowCostPerKg;
                return Math.max(cost, minCharge);
            default:
                throw new IllegalArgumentException("Unknown region: " + region);
        }
    }
}

