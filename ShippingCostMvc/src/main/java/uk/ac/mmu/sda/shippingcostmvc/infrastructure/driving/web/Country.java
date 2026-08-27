package uk.ac.mmu.sda.shippingcostmvc.infrastructure.driving.web;

public record Country(String code, String name) implements Comparable<Country> {
    public Country {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Region code must not be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Region name must not be null or blank");
        }
    }

    @Override
    public int compareTo(Country other) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.name, other.name);
    }
}
