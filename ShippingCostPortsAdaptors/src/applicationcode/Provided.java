package applicationcode;

public interface Provided {
    static Provided create(Required required) {
        return new ShippingCostCalculator(required);
    }

    double calculate(Region region, double weight);
}

