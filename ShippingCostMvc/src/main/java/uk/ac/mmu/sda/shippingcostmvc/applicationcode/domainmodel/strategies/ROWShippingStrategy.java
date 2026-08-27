package uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.strategies;

import uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.ShippingCostStrategy;

public class ROWShippingStrategy implements ShippingCostStrategy {
    private final double minCharge;
    private final double costPerKg;

    public ROWShippingStrategy(double minCharge, double costPerKg) {
        this.minCharge = minCharge;
        this.costPerKg = costPerKg;
    }

    @Override
    public double calculate(double weight) {
        return Math.max(minCharge, weight * costPerKg);
    }
}
