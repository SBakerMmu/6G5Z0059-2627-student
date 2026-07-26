package applicationcode.usecase.listavailablecountries;

import java.util.Set;

class UseCase implements Provided {
    private final Required required;

    UseCase(Required required) {
        this.required = required;
    }

    @Override
    public Set<String> list() {
        return Set.copyOf(required.getCountryCodes());
    }
}
