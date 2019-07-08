package ro.sci.gr14.web;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.security.RegistrationForm;

/**
 * Controller implementation for {@link BaseUser} instances
 * All handling methods on this controller are relative to the /register path
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {

    private IBaseUserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor taking two parameters
     *
     * @param userRepo        an instance of {@link IBaseUserRepository}
     * @param passwordEncoder the Springframework Security {@link PasswordEncoder}
     */
    public RegistrationController(
            IBaseUserRepository userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @return register
     */
    @GetMapping
    public String registerForm( ){
        log.info("Log -- Register GetMapping");
        return "register";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.POST)
     *
     * @param form   instance of {@link RegistrationForm}
     * @param errors the Springframework Validation {@link Errors}
     * @param model  the Spring MVC {@link Model}
     * @return redirect:/login
     */
    @PostMapping
    public String processRegistration(RegistrationForm form, Errors errors, Model model){
        if (errors.hasErrors()) {
            log.info("Errors" + errors.toString());
            return "register";
        }
        log.info("Log -- Register post");
        BaseUser baseUser = form.toUser(passwordEncoder);
        try {
            userRepo.save(baseUser);
        } catch (Exception e) {
            log.info("Errors: " + e.toString());
            model.addAttribute("exception", e);
            model.addAttribute("baseUser", baseUser);
            return "register";
        }
        return "redirect:/login";
    }
}