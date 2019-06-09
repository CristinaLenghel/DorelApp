package ro.sci.gr14.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.sci.gr14.data.IBaseUserRepository;

@Controller
@RequestMapping("/register")
@Slf4j

public class RegistrationController {

    private IBaseUserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(
            IBaseUserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        log.info("Log -- Register GetMapping");
        return "register";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Errors" +errors.toString());
            return "register";
        }
        log.info("Log -- Register post");
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}