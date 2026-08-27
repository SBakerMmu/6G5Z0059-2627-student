package uk.ac.mmu.sda.shippingcostmvc.infrastructure.driving.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShippingCostGetController {

    @GetMapping("/")
    public String showForm(Model model) {
        return "shipping-cost";
    }
}
