package applicationcode;

import java.util.StringJoiner;

public class ShippingCost {
    private final Region region;
    private final double minCharge;
    private final double costPerKg;

    public ShippingCost(Region region, double minCharge, double costPerKg) {
        this.region = region;
        this.minCharge = minCharge;
        this.costPerKg = costPerKg;
    }

    public Region getRegion() {
        return region;
    }
    public double getMinCharge() {
        return minCharge;
    }

    public double getCostPerKg() {
        return costPerKg;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ShippingCost.class.getSimpleName() + "[", "]")
                .add("region=" + region)
                .add("minCharge=" + minCharge)
                .add("costPerKg=" + costPerKg)
                .toString();
    }


}

