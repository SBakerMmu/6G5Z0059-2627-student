package infrastructure.driven;

import applicationcode.Region;
import applicationcode.Required;
import applicationcode.ShippingCost;

public class ShippingCostDatabaseAdapter implements Required {

    private final ShippingCostDatabase database;

    public ShippingCostDatabaseAdapter(ShippingCostDatabase database) {
        this.database = database;
    }

    @Override
    public ShippingCost getShippingCostForRegion(Region region) {
        return new ShippingCost(
                region,
                database.minChargeMap.get(region),
                database.costPerKgMap.get(region)
        );
    }
}
