package infrastructure.driven;

import applicationcode.Region;
import applicationcode.ShippingCost;

import java.util.List;

public class ShippingCostRepositoryAdapter implements applicationcode.ShippingCostRepository {

    private final ShippingCostDatabase database;

    public ShippingCostRepositoryAdapter(ShippingCostDatabase database) {
        this.database = database;
    }


    @Override
    public void addShippingCost(ShippingCost shippingCost) {
        if(database.regions.add(shippingCost.getRegion()))
        {
            database.costPerKgMap.put(shippingCost.getRegion(), shippingCost.getCostPerKg());
            database.minChargeMap.put(shippingCost.getRegion(), shippingCost.getMinCharge());
        }
    }

    @Override
    public void updateShippingCost(ShippingCost shippingCost) {
        database.costPerKgMap.replace(shippingCost.getRegion(), shippingCost.getCostPerKg());
        database.minChargeMap.replace(shippingCost.getRegion(), shippingCost.getMinCharge());
    }

    @Override
    public void deleteShippingCost(Region region) {
        database.costPerKgMap.remove(region);
        database.minChargeMap.remove(region);
        database.regions.remove(region);
    }

    @Override
    public List<ShippingCost> getAllShippingCosts() {
        return database.regions.stream().map(region -> {
            double costPerKg = database.costPerKgMap.get(region);
            double minCharge = database.minChargeMap.get(region);
            ShippingCost shippingCost = new ShippingCost(region, minCharge, costPerKg);
            return shippingCost;
        }).toList();
    }
}
