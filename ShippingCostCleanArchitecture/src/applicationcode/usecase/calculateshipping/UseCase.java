package applicationcode.usecase.calculateshipping;

import applicationcode.domainmodel.Region;
import applicationcode.domainmodel.ShippingRegion;
import applicationcode.domainmodel.ShippingRegionFactory;

class UseCase implements Provided {
    private final Required required;

    UseCase(Required required) {
        this.required = required;
    }

    @Override
    public double calculate(String countryCode, double weight) {
        if (countryCode == null || countryCode.isEmpty()) {
            throw new IllegalArgumentException("Country code cannot be null or empty");
        }

        String regionCode = required.getRegionCode(countryCode);

        if (regionCode == null || regionCode.isEmpty()) {
            throw new IllegalArgumentException("No Region code found for country: " + countryCode);
        }

        ShippingCost shippingCost = required.getShippingCostForRegion(regionCode);

        if (shippingCost == null) {
            throw new IllegalArgumentException("No shipping cost found for region: " + regionCode);
        }

        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero");
        }

        Region region = Region.valueOf(regionCode);

        ShippingRegion shippingRegion = ShippingRegionFactory.create(region, shippingCost.getMinCharge(), shippingCost.getCostPerKg());

        return shippingRegion.calculate(weight);
    }
}
