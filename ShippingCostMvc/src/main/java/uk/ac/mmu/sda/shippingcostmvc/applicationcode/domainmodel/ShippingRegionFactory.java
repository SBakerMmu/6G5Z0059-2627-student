package uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel;


import uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.strategies.EURShippingStrategy;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.strategies.ROWShippingStrategy;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.strategies.UKShippingStrategy;

public class ShippingRegionFactory {
    public static ShippingRegion create(Region region, double minCharge, double costPerKg) {
        return new ShippingRegion(region, chooseStrategy(region, minCharge, costPerKg));
    }

    private static ShippingCostStrategy chooseStrategy(Region region, double minCharge, double costPerKg) {
        return switch (region) {
            case UK -> new UKShippingStrategy();
            case EUR -> new EURShippingStrategy(costPerKg);
            case ROW -> new ROWShippingStrategy(minCharge, costPerKg);
            default -> throw new IllegalArgumentException("Unknown region: " + region);
        };
    }
}

