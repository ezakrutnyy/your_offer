package nex.zktn.your_offer.controller;

import nex.zktn.your_offer.entity.User;
import nex.zktn.your_offer.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String registrationForm() {
        return "registration";
    }


    @PostMapping("/registration")
    public String registrationUser(User user, Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("errorMessage", "Error. This user exist already!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{activationCode}")
    public String activation(Model model, @PathVariable String activationCode) {
        boolean isActivate = userService.isActivateUser(activationCode);
        if (isActivate) {
            model.addAttribute("successMessage", "User successfully activated!");
        } else {
            model.addAttribute("errorMessage", "Activation code is not found!");
        }
        return "login";
    }
}
