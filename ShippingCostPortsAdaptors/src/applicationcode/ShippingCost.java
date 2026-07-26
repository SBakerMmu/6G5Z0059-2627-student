package applicationcode;

public class ShippingCost {
    private final Region region;
    private final double minCharge;
    private final double costPerKg;

    public ShippingCost(Region region, double minCharge, double costPerKg) {
        this.region = region;
        this.minCharge = minCharge;
        this.costPerKg = costPerKg;
    }

    public double getMinCharge() {
        return minCharge;
    }
    public double getCostPerKg() {
        return costPerKg;
    }
    public Region getRegion() { return region;  }

}

