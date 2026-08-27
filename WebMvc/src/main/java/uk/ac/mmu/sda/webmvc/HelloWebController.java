package uk.ac.mmu.sda.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;


@Controller
@RequestMapping("/views")
class HelloWebController {
    @GetMapping("/namedview")
    public String getNamedView(Model model) {

        model.addAttribute("message", "Hello, World!");
        return "helloWorldView";
    }
    @GetMapping("/viewObject")
    public View getTypedView(Model model) {
        model.addAttribute("message", "Hello, World!");
        return new HelloWorldView();
    }


}
