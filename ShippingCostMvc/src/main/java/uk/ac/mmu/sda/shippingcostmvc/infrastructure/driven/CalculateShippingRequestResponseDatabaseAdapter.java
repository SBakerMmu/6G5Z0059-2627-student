package uk.ac.mmu.sda.shippingcostmvc.infrastructure.driven;


import org.springframework.stereotype.Component;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.Country;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.Required;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.ShippingCost;

@Component
public class CalculateShippingRequestResponseDatabaseAdapter implements Required
{

    private final ShippingCostDatabase database;

    public CalculateShippingRequestResponseDatabaseAdapter(ShippingCostDatabase database) {
        this.database = database;
    }

    @Override
    public Country getCountry(String countryCode) {
        return new Country(countryCode, database.countryMap.get(countryCode), database.countryRegionMap.get(countryCode));
    }

    @Override
    public ShippingCost getShippingCostForRegion(String regionCode) {
        return new ShippingCost(database.minChargeMap.get(regionCode),database.costPerKgMap.get(regionCode));
    }
}

