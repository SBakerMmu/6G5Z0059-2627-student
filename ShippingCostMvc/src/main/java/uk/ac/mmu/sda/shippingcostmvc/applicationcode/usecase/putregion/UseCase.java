package uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.putregion;

import java.util.Set;

public class UseCase implements Provided {
    private final Required required;

    public UseCase(Required required) {
        this.required = required;
    }

    @Override
    public void put(Set<Region> regions) {
        required.put(regions);
    }
}
