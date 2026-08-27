package uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.strategies;

import uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.ShippingCostStrategy;

public class UKShippingStrategy implements ShippingCostStrategy {
    private static final double ZERO = 0.0d;

    @Override
    public double calculate(double weight) {
        return ZERO;
    }
}
