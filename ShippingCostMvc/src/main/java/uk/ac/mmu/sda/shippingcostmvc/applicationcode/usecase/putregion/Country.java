package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.putregion;

import java.util.Objects;

public record Country(String code, String name) {
    public Country {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Code must not be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Country country)) return false;
        return Objects.equals(code, country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
