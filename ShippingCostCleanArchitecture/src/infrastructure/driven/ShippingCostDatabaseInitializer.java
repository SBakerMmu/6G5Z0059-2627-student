package infrastructure.driven;

import applicationcode.usecase.putregion.Country;
import applicationcode.usecase.putregion.Provided;
import applicationcode.usecase.putregion.Region;
import java.util.List;
import java.util.Set;

public class ShippingCostDatabaseInitializer {
    private final Provided putRegions;

    public ShippingCostDatabaseInitializer(Provided putRegions) {
        this.putRegions = putRegions;
    }
        // Define regions and their properties
    public void initializeDb(){

        Region uk = new Region("UK", "United Kingdom", 0.0, 0.0);
        uk.addCountry(new Country("GB", "United Kingdom"));

        Region eur = new Region("EUR", "Europe", 0.0, 1.25);
        eur.addCountries(List.of(
                new Country("AL", "Albania"),
                new Country("AD", "Andorra"),
                new Country("AT", "Austria"),
                new Country("BY", "Belarus"),
                new Country("BE", "Belgium"),
                new Country("BA", "Bosnia and Herzegovina"),
                new Country("BG", "Bulgaria"),
                new Country("HR", "Croatia"),
                new Country("CY", "Cyprus"),
                new Country("CZ", "Czech Republic"),
                new Country("DK", "Denmark"),
                new Country("EE", "Estonia"),
                new Country("FI", "Finland"),
                new Country("FR", "France"),
                new Country("DE", "Germany"),
                new Country("GR", "Greece"),
                new Country("HU", "Hungary"),
                new Country("IS", "Iceland"),
                new Country("IE", "Ireland"),
                new Country("IT", "Italy"),
                new Country("LV", "Latvia"),
                new Country("LI", "Liechtenstein"),
                new Country("LT", "Lithuania"),
                new Country("LU", "Luxembourg"),
                new Country("MT", "Malta"),
                new Country("MD", "Moldova"),
                new Country("MC", "Monaco"),
                new Country("ME", "Montenegro"),
                new Country("NL", "Netherlands"),
                new Country("MK", "North Macedonia"),
                new Country("NO", "Norway"),
                new Country("PL", "Poland"),
                new Country("PT", "Portugal"),
                new Country("RO", "Romania"),
                new Country("RU", "Russia"),
                new Country("SM", "San Marino"),
                new Country("RS", "Serbia"),
                new Country("SK", "Slovakia"),
                new Country("SI", "Slovenia"),
                new Country("ES", "Spain"),
                new Country("SE", "Sweden"),
                new Country("CH", "Switzerland"),
                new Country("UA", "Ukraine"),
                new Country("VA", "Vatican City")
        ));

        Region row = new Region("ROW", "Rest of World", 10.0, 5.5);
        row.addCountries(List.of(
                new Country("US", "United States"),
                new Country("CA", "Canada"),
                new Country("AU", "Australia"),
                new Country("NZ", "New Zealand"),
                new Country("CN", "China"),
                new Country("JP", "Japan"),
                new Country("IN", "India"),
                new Country("BR", "Brazil"),
                new Country("ZA", "South Africa"),
                new Country("MX", "Mexico")
        ));

        //execute the use case
        putRegions.put(Set.of(uk, eur, row));

    }
}
