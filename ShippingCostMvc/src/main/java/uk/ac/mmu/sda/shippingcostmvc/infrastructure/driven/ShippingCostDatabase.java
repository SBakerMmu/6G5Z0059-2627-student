package uk.ac.mmu.sda.shippingcostmvc.infrastructure.driven;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShippingCostDatabase {

    final Map<String, Double> costPerKgMap = new HashMap<>();
    final Map<String, Double> minChargeMap = new HashMap<>();
    final Map<String, String> countryRegionMap = new HashMap<>();
    final Map<String, String> regionMap = new HashMap<>();
    final Map<String, String> countryMap = new HashMap<>();
}
