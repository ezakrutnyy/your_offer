package nex.zktn.your_offer.controller;

import nex.zktn.your_offer.entity.Role;
import nex.zktn.your_offer.entity.User;
import nex.zktn.your_offer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("url", "users");
        return "user/list";
    }

    @GetMapping("/update/{id}")
    public String updateTemplate(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userRepository.getById(id));
        model.addAttribute("url", "users");
        model.addAttribute("roles", Role.values());
        return "user/update";
    }

    @PostMapping("/update/{id}")
    public String update(
            @RequestParam String username,
            @RequestParam(required = false) Boolean active,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {

        user.setUsername(username);
        user.setActive(active != null && active);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);

        return "redirect:/user";
    }

}
