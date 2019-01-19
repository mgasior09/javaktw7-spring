package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleWebController {
    @RequestMapping("/hi")
    public String firstMethod(Model model) {
        model.addAttribute("name", "Magda");
        return "hello";
    }
}
