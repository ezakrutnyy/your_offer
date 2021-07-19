package nex.zktn.your_offer.controller;

import nex.zktn.your_offer.entity.Role;
import nex.zktn.your_offer.entity.User;
import nex.zktn.your_offer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Objects;

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
        final User userDB = userService.findByUsername(user.getUsername());
        if (Objects.nonNull(userDB)) {
            model.addAttribute("errorMessage", "Error. This user exist already!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);
        return "redirect:/login";
    }
}
