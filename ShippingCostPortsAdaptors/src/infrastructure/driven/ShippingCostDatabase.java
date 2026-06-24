package infrastructure.driven;

import applicationcode.Region;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShippingCostDatabase {
    public final Set<Region> regions = new HashSet<>();
    public final Map<Region, Double> costPerKgMap = new EnumMap<>(Region.class);
    public final Map<Region, Double> minChargeMap = new EnumMap<>(Region.class);
}
