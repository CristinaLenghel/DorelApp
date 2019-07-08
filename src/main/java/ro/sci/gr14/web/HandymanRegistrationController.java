package ro.sci.gr14.web;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.model.Handyman;
import ro.sci.gr14.model.RegistrationFormValidator;
import ro.sci.gr14.security.HandymanRegistrationForm;
import ro.sci.gr14.security.SecurityService;

import javax.validation.Valid;

/**
 * Controller implementation for {@link Handyman} instances registration
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
@Slf4j
public class HandymanRegistrationController {
    private IHandymanRepository handymanRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationFormValidator registrationFormValidator;
    @Autowired
    private SecurityService securityService;

    /**
     * Constructor taking two parameters
     *
     * @param handymanRepo    an instance of {@link IHandymanRepository}
     * @param passwordEncoder the Springframework Security {@link PasswordEncoder}
     */
    public HandymanRegistrationController(
            IHandymanRepository handymanRepo, PasswordEncoder passwordEncoder){
        this.handymanRepo = handymanRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param model                    the Spring MVC {@link Model}
     * @param handymanRegistrationForm instance of {@link HandymanRegistrationForm}
     * @return register/registerHandyman
     */
    @GetMapping("/register/registerHandyman")
    public String RegisterForm(Model model, HandymanRegistrationForm handymanRegistrationForm){
        log.info("Log -- Handyman Register Get");
        model.addAttribute("handymanRegisterForm", handymanRegistrationForm);
        return "register/registerHandyman";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.POST)
     * Registers a new {@link Handyman} for the data provided by the given {@link HandymanRegistrationForm}
     * An interface is used to bind request parameters
     *
     * @param model                the Spring MVC {@link Model}
     * @param handymanRegisterForm instance of {@link HandymanRegistrationForm}
     * @param bindingResult        the result of the binding operation
     * @return redirect:/handyman
     */
    @PostMapping("/register/registerHandyman")
    public String processRegistration(Model model, @ModelAttribute("handymanRegisterForm") @Valid HandymanRegistrationForm handymanRegisterForm, BindingResult bindingResult){
        registrationFormValidator.validate(handymanRegisterForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register/registerHandyman";
        }

        log.info("Log -- Handyman Register post");
        Handyman handyman = handymanRegisterForm.toHandyman(passwordEncoder);
        log.info("form.toHandyman -- " + handyman);
        try {
            handymanRepo.save(handyman);
        } catch (Exception e) {
            log.info("Errors" + e.toString());
            return "register/registerHandyman";
        }

        securityService.autoLogin(handymanRegisterForm.getUsername(), handymanRegisterForm.getConfirmPassword());
        return "redirect:/handyman";
    }

}