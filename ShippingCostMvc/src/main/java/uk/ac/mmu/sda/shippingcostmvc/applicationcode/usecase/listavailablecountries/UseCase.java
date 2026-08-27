package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.listavailablecountries;


import java.util.Set;

public class UseCase implements Provided {
    private final Required required;

    public UseCase(Required required) {
        this.required = required;
    }

    @Override
    public Set<Country> list() {
        return Set.copyOf(required.getCountries());
    }
}
