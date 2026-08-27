package uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.strategies;

import uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.ShippingCostStrategy;

public class EURShippingStrategy implements ShippingCostStrategy {
    private final double costPerKg;

    public EURShippingStrategy(double costPerKg) {
        this.costPerKg = costPerKg;
    }

    @Override
    public double calculate(double weight) {
        return weight * costPerKg;
    }
}
