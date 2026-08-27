package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.listavailablecountries;


public record Country(String code, String name) {
    public Country {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Code must not be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
    }
}
