package nex.zktn.your_offer.controller;

import nex.zktn.your_offer.entity.Role;
import nex.zktn.your_offer.entity.User;
import nex.zktn.your_offer.repository.UserRepository;
import nex.zktn.your_offer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("url", "users");
        return "user/list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update/{id}")
    public String updateTemplate(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userRepository.getById(id));
        model.addAttribute("url", "users");
        model.addAttribute("roles", Role.values());
        return "user/update";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update/{id}")
    public String update(
            @RequestParam String username,
            @RequestParam(required = false) Boolean active,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {
        userService.save(user, username, active, form);
        return "redirect:/user";
    }


    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/profile")
    public String profileForm(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email) {
        userService.updateProfile(user, email, password);
        return "redirect:user/profile";
    }

}
