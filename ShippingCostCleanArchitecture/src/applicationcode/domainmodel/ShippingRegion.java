package applicationcode.domainmodel;


public class ShippingRegion {
    private final ShippingCostStrategy strategy;
    private Region region;

    public ShippingRegion(Region region, ShippingCostStrategy strategy) {
        this.region = region;
        this.strategy = strategy;
    }

    public double calculate(double weight) {
        return strategy.calculate(weight);
    }

    @Override
    public String toString() {
        return String.format("ShippingRegion{region=%s, strategy=%s}", region, strategy.getClass().getSimpleName()
        );
    }
}
