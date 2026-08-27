package uk.ac.mmu.sda.shippingcostmvc.infrastructure.driving.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.Country;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.Provided;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.Request;
import uk.ac.mmu.sda.shippingcostmvc.applicationcode.usecase.calculateshipping.Response;

@Controller
public class ShippingCostPostController {

    private final Provided calculateShipping;

    public ShippingCostPostController(Provided calculateShipping) {
        this.calculateShipping = calculateShipping;
    }


    @PostMapping("/")
    public String calculate(@ModelAttribute Request request, Model model) {
        try {
            //Here we can ask the framework to map HTML form data directly to our Use Case Request object using the @ModelAttribute annotation
            Response response = calculateShipping.handle(request);
            //Map the Use Case Response object to a presentation DTO and insert into the Model
            model.addAttribute("shippingCost", new ShippingCost(response.country().code(), response.country().name(), response.country().regionCode(), response.weight(), response.cost()));
        } catch (IllegalArgumentException exception) {
            model.addAttribute("error", exception.getMessage());
        }
        return "shipping-cost";
    }

}
