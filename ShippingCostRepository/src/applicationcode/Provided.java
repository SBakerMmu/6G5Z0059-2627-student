package applicationcode;

import java.util.List;

public interface Provided {
    static Provided create(ShippingCostRepository required) {
        return new ShippingCostAdministrator(required);
    }

    void addShippingCost(Region region, double minCharge, double costPerKg);
    void updateShippingCost(Region region, double minCharge, double costPerKg);
    void deleteShippingCost(Region region);
    List<ShippingCost> getAllShippingCosts();
}

