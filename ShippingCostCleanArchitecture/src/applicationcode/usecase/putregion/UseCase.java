package applicationcode.usecase.putregion;

import java.util.Set;

class UseCase implements Provided {
    private final Required required;

    UseCase(Required required) {
        this.required = required;
    }

    @Override
    public void put(Set<Region> regions) {
        required.put(regions);
    }
}
