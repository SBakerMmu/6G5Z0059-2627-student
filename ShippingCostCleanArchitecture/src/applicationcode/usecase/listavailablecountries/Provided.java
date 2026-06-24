package applicationcode.usecase.listavailablecountries;


import java.util.Set;

public interface Provided {
    static Provided create(Required required) {
        return new UseCase(required);
    }
    Set<String> list();
}

