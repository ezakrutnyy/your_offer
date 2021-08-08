package nex.zktn.your_offer.controller;

import nex.zktn.your_offer.entity.dto.CaptchaResponseDto;
import nex.zktn.your_offer.entity.User;
import nex.zktn.your_offer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {

    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private final UserService userService;

    private final RestTemplate restTemplate;

    @Autowired
    public RegistrationController(UserService userService,
                                  RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }


    @GetMapping("/registration")
    public String registrationForm() {
        return "registration";
    }

    @Value("${captcha.secret}")
    private String captchaSecret;

    @PostMapping("/registration")
    public String registrationUser(@Valid User user,
                                   BindingResult bindingResult,
                                   Model model,
                                   @RequestParam("g-recaptcha-response") String captchaResponse) {
        final CaptchaResponseDto captchaResponseDto = restTemplate.postForObject(String.format(CAPTCHA_URL, captchaSecret, captchaResponse),
                Collections.emptyList(), CaptchaResponseDto.class);
        if (Objects.nonNull(captchaResponseDto) && captchaResponseDto.isNotSuccess()) {
            model.addAttribute("captchaError", "Fill captcha!");
        }

        if (user.getPassword() != null && user.getPasswordConfirm() != null
                && !user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordConfirmError", "Passwords are different!");

        }

        if (bindingResult.hasErrors() || (Objects.nonNull(captchaResponseDto) && captchaResponseDto.isNotSuccess())) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            error -> error.getField() + "Error",
                            FieldError::getDefaultMessage
                    ));
            model.mergeAttributes(errors);
            return "registration";
        } else {
            if (!userService.createUser(user)) {
                model.addAttribute("errorMessage", "Error. This user exist already!");
                return "registration";
            }
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
