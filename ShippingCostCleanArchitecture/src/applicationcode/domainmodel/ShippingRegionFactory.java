package applicationcode.domainmodel;


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

