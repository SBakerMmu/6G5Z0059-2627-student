package infrastructure.driven;

import java.util.HashMap;
import java.util.Map;

public class ShippingCostDatabase {

    final Map<String, Double> costPerKgMap = new HashMap<>();
    final Map<String, Double> minChargeMap = new HashMap<>();
    final Map<String, String> countryRegionMap = new HashMap<>();
    final Map<String, String> regionMap = new HashMap<>();
    final Map<String, String> countryMap = new HashMap<>();
}
