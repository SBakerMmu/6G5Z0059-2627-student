package infrastructure.driven;

import applicationcode.Region;

import java.util.*;

//Simulate a database with in-memory data structures
public class ShippingCostDatabase {
    public final Set<Region> regions = new HashSet<>();
    public final Map<Region, Double> costPerKgMap = new EnumMap<>(Region.class);
    public final Map<Region, Double> minChargeMap = new EnumMap<>(Region.class);
}
