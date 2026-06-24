package applicationcode.usecase.putregion;


import java.util.Set;

public interface Provided {
    static Provided create(Required required) {
        return new UseCase(required);
    }
    void put(Set<Region> regions);
    default void put(Region region) {
        put(Set.of(region));
    }
}

