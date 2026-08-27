package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping;

public record Request(String countryCode, double weight) {
    public Request {

        if (countryCode == null || countryCode.isBlank()) {
            throw new IllegalArgumentException("countryCode must not be null or blank");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("weight must be greater than 0");
        }
    }
}



