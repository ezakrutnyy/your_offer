package nex.zktn.your_offer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String index(@Value("${username.default}") String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("url", "index");
        return "index";
    }
}
