package applicationcode;

class UKShippingStrategy implements ShippingCostStrategy {
    private static final double ZERO = 0.0d;

    UKShippingStrategy() {
    }

    @Override
    public double calculate(double weight) {
        return ZERO;
    }
}
