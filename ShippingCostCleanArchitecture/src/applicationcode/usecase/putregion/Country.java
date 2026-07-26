package applicationcode.usecase.putregion;

import java.util.Objects;

public class Country {
    private final String code;
    private final String name;

    public Country(String code, String name) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Region code must not be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Region name must not be null or blank");
        }
        this.code = code;
        this.name = name;
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

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
