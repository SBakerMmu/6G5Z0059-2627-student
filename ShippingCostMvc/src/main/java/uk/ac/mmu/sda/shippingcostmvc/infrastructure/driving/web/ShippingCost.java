package uk.ac.mmu.sda.shippingcostmvc.infrastructure.driving.web;

import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.Country;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.Request;

public record ShippingCost(String code, String name, String region, double weight, double cost) {
}

