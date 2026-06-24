package applicationcode.usecase.listavailablecountries;

import java.util.Set;

public interface Required {
    Set<String> getRegionCodes();
    Set<String> getCountryCodes();
}

