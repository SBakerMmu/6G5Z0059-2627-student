package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping;

import uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.Region;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.ShippingRegion;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.domainmodel.ShippingRegionFactory;

public class UseCase implements Provided {
    private final Required required;

    public UseCase(Required required) {
        this.required = required;
    }

    @Override
    public Response handle(Request request) {

        Country country = required.getCountry(request.countryCode());

        ShippingCost shippingCost = required.getShippingCostForRegion(country.regionCode());

        if (shippingCost == null) {
            throw new IllegalArgumentException("No shipping cost found for region: " + country.regionCode());
        }

        Region region = Region.valueOf(country.regionCode());

        ShippingRegion shippingRegion = ShippingRegionFactory.create(region, shippingCost.minCharge(), shippingCost.costPerKg());

        double cost = shippingRegion.calculate(request.weight());

        return new Response(
                request,
                country,
                cost
        );
    }
}

