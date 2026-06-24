package applicationcode.usecase.calculateshipping;


public interface Provided {
    static Provided create(Required required) {
        return new UseCase(required);
    }

    double calculate(String countryCode, double weight);
}

