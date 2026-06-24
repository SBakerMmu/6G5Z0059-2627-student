package applicationcode;

import java.util.List;

public interface ShippingCostRepository {
    void addShippingCost(ShippingCost shippingCost);
    void updateShippingCost(ShippingCost shippingCost);
    void deleteShippingCost(Region region);
    List<ShippingCost> getAllShippingCosts();
}
