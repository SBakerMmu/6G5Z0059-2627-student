package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping;


public record Country(String code, String name, String regionCode) {
    public Country {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Code must not be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
        if (regionCode == null || regionCode.isBlank()) {
            throw new IllegalArgumentException("Region code must not be null or blank");
        }

    }
}
