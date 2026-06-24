package applicationcode.usecase.calculateshipping;

public interface Required {
    String getRegionCode(String countryCode);

    ShippingCost getShippingCostForRegion(String regionCode);
}

