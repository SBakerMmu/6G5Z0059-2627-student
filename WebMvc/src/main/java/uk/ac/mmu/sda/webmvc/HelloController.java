package uk.ac.mmu.sda.webmvc;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/views")
class HelloController {

  @GetMapping("/viewObject")
  public View getTypedView(Model model) {
    model.addAttribute("message", "Hello, World!");
    return new HelloWorldView("Hello World using View object");
  }

  @GetMapping("/namedview")
  public String getNamedView(Model model) {
    model.addAttribute("message", "Hello, World!");
    return "helloWorldView";
  }
}
