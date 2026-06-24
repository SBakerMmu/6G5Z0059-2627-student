package infrastructure.driven;


import java.util.Set;
import applicationcode.usecase.listavailablecountries.Required;

public class ListAvailableCountriesDatabaseAdapter implements Required
{

    private final ShippingCostDatabase database;

    public ListAvailableCountriesDatabaseAdapter(ShippingCostDatabase database) {
        this.database = database;
    }

    @Override
    public Set<String> getRegionCodes() {
        return Set.copyOf(database.countryRegionMap.values());
    }

    @Override
    public Set<String> getCountryCodes() {
        return Set.copyOf(database.countryRegionMap.keySet());
    }

}
