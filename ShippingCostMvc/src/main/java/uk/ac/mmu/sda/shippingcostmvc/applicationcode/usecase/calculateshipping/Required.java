package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping;

public interface Required {
    Country getCountry(String countryCode);

    ShippingCost getShippingCostForRegion(String regionCode);
}

