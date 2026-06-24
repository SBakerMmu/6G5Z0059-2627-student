package infrastructure.driven;



import applicationcode.usecase.calculateshipping.ShippingCost;
import applicationcode.usecase.calculateshipping.Required;
public class CalculateShippingDatabaseAdapter implements Required
{
    private final ShippingCostDatabase database;

    public CalculateShippingDatabaseAdapter(ShippingCostDatabase database) {
        this.database = database;
    }

    @Override
    public String getRegionCode(String countryCode) {
        return database.countryRegionMap.get(countryCode);
    }

    @Override
    public ShippingCost getShippingCostForRegion(String regionCode) {
        return new ShippingCost(database.minChargeMap.get(regionCode),database.costPerKgMap.get(regionCode));
    }

}
