package applicationcode.usecase.putregion;

import java.util.*;

public class Region {
    private final String code;
    private final String name;
    private final double minCharge;
    private final double costPerKg;
    private final Set<Country> countries = new HashSet<>();

    public Region(String code, String name, double minCharge, double costPerKg) {

        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Region code must not be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Region name must not be null or blank");
        }
        if (minCharge < 0) {
            throw new IllegalArgumentException("minCharge must not be negative");
        }
        if (costPerKg < 0) {
            throw new IllegalArgumentException("costPerKg must not be negative");
        }

        this.code = code;
        this.name = name;
        this.minCharge = minCharge;
        this.costPerKg = costPerKg;
    }



    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Region region)) return false;
        return code.equalsIgnoreCase(region.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    public double getMinCharge() {
        return minCharge;
    }
    public double getCostPerKg() {
        return costPerKg;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void addCountries(List<Country> countries) {
        if (countries == null) {
            throw new IllegalArgumentException("Countries list must not be null");
        }
        for (Country country : countries) {
            addCountry(country);
        }

    }
    public void addCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country must not be null");
        }
        if(!countries.add(country))
        {
            throw new IllegalArgumentException("Country already exists in the region");
        }
    }

    public Set<Country> getCountries() {
        return Set.copyOf(countries);
    }
}

