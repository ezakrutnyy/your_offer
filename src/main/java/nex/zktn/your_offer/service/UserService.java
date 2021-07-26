package nex.zktn.your_offer.service;

import nex.zktn.your_offer.entity.Role;
import nex.zktn.your_offer.entity.User;
import nex.zktn.your_offer.repository.UserRepository;
import nex.zktn.your_offer.util.GeneratorID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static nex.zktn.your_offer.util.GeneratorID.generateUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final MailSenderService mailSenderService;

    public UserService(UserRepository userRepository, MailSenderService mailSenderService) {
        this.userRepository = userRepository;
        this.mailSenderService = mailSenderService;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user, String username, Boolean active, Map<String, String> form) {
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
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public boolean addUser(User user) {
        final User userDB = findByUsername(user.getUsername());
        if (Objects.nonNull(userDB)) {
            return false;
        }
        user.setActive(false);
        user.setActivationCode(generateUID());
        user.setRoles(Collections.singleton(Role.USER));

        userRepository.save(user);

        if (StringUtils.isNotBlank(user.getEmail())) {
            sendEmail(user);
        }
        return true;
    }

    public boolean isActivateUser(String activationCode) {
        final User user = userRepository.findByActivationCode(activationCode);
        if (Objects.isNull(user)) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);

        return true;
    }

    public void updateProfile(User user, String email, String password) {
        final String currentEmail = user.getEmail();

        final boolean isChangeEmail = StringUtils.isNotBlank(currentEmail) && !currentEmail.equals(email);
        if (isChangeEmail) {
            user.setEmail(email);
            user.setActivationCode(GeneratorID.generateUID());
            user.setActive(false);
        }

        final String currentPassword = user.getPassword();
        final boolean isChangePassword = StringUtils.isNotBlank(currentPassword) && !currentPassword.equals(password);

        if (isChangePassword) {
            user.setPassword(password);
        }

        if (isChangeEmail || isChangePassword) {
            userRepository.save(user);
            if (isChangeEmail) {
                sendEmail(user);
            }
        }

    }


    private void sendEmail(User user) {
        mailSenderService.send(user.getEmail(), "Activation code",
                String.format("Hello %s! Welcome to portal 'Your Offer' \n" +
                        "For activation your account, please visit next link " +
                        "http://localhost:9990/activate/%s", user.getUsername(), user.getActivationCode()));
    }
}
