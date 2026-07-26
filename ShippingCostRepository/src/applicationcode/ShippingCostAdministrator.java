package applicationcode;

import java.util.List;
import java.util.Optional;

class ShippingCostAdministrator implements Provided {
    private final ShippingCostRepository required;

    public ShippingCostAdministrator(ShippingCostRepository required) {
        this.required = required;
    }


    @Override
    public void addShippingCost(Region region, double minCharge, double costPerKg) {
        if(getAllShippingCosts().stream().anyMatch(sc -> sc.getRegion().equals(region))) {
            throw new IllegalArgumentException("Shipping cost for region already exists");
        }
        ShippingCost shippingCost = new ShippingCost(region, minCharge, costPerKg);
        required.addShippingCost(shippingCost);
    }

    @Override
    public void updateShippingCost(Region region, double minCharge, double costPerKg) {
        Optional<ShippingCost> cost = getAllShippingCosts().stream().filter(sc -> sc.getRegion().equals(region)).findFirst();
        if(cost.isEmpty()) {
            throw new IllegalArgumentException("Shipping cost for region not found");
        }
        ShippingCost shippingCost = new ShippingCost(region, minCharge, costPerKg);
        required.updateShippingCost(shippingCost);
    }

    @Override
    public void deleteShippingCost(Region region) {
        Optional<ShippingCost> cost = getAllShippingCosts().stream().filter(sc -> sc.getRegion().equals(region)).findFirst();
        if(cost.isPresent()) {
            required.deleteShippingCost(region);
        }
    }

    @Override
    public List<ShippingCost> getAllShippingCosts() {
        return List.copyOf(required.getAllShippingCosts());
    }
}
