package uk.ac.mmu.sda.shippingcostmvc.infrastructure.driven;


import org.springframework.stereotype.Component;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.listavailablecountries.Country;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.listavailablecountries.Required;

import java.util.HashSet;
import java.util.Set;


@Component
public class ListAvailableCountriesDatabaseAdapter implements Required
{
    private final ShippingCostDatabase database;

    public ListAvailableCountriesDatabaseAdapter(ShippingCostDatabase database) {
        this.database = database;
    }

    @Override
    public Set<Country> getCountries() {
        Set<Country> countries = new HashSet<>();
        for(var key : database.countryMap.keySet())
        {
            countries.add(new Country(key, database.countryMap.get(key)));
        }
        return countries;
    }

}

