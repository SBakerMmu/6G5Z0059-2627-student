package uk.ac.mmu.sda.shippingcostmvc.infrastructure.driven;


import org.springframework.stereotype.Component;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.putregion.Country;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.putregion.Region;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.putregion.Required;

import java.util.Set;

@Component
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
        database.countryRegionMap.put(country.code(), region.getCode());
        database.countryMap.put(country.code(), country.name());
    }
}

