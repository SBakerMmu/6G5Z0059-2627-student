package infrastructure.driven;



import applicationcode.usecase.putregion.Country;
import applicationcode.usecase.putregion.Region;
import applicationcode.usecase.putregion.Required;
import java.util.Set;

public class PutRegionDatabaseAdapter implements Required
{

    private final ShippingCostDatabase database;

    public PutRegionDatabaseAdapter(ShippingCostDatabase database) {
        this.database = database;
    }

    @Override
    public void put(Set<Region> regions) {
        for (Region region : regions) {
            put(region);
        }
    }
    private void put(Region region) {
        database.regionMap.put(region.getCode(), region.getName());
        database.costPerKgMap.put(region.getCode(), region.getCostPerKg());
        database.minChargeMap.put(region.getCode(), region.getMinCharge());

        for (Country country : region.getCountries()) {
            put(region, country);
        }
    }

    private void put(Region region, Country country) {
        database.countryRegionMap.put(country.getCode(), region.getCode());
        database.countryMap.put(country.getCode(), country.getName());
    }
}
