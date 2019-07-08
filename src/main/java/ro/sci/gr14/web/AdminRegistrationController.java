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
import ro.sci.gr14.data.IAdminRepository;
import ro.sci.gr14.model.Admin;
import ro.sci.gr14.model.RegistrationFormValidator;
import ro.sci.gr14.security.AdminRegistrationForm;
import ro.sci.gr14.security.SecurityService;

import javax.validation.Valid;

/**
 * This class is a controller implementation for {@link Admin} instances registration
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
public class AdminRegistrationController {

    private IAdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationFormValidator registrationFormValidator;

    @Autowired
    private SecurityService securityService;

    /**
     * Constructor taking two parameters
     *
     * @param adminRepository an instance of {@link IAdminRepository}
     * @param passwordEncoder the Springframework Security {@link PasswordEncoder}
     */
    public AdminRegistrationController(
            IAdminRepository adminRepository, PasswordEncoder passwordEncoder){
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param model                 the Spring MVC {@link Model}
     * @param adminRegistrationForm instance of {@link AdminRegistrationForm}
     * @return register/registerAdmin
     */
    @GetMapping("/register/registerAdmin")
    public String registerForm(Model model, AdminRegistrationForm adminRegistrationForm){
        log.info("Log -- Admin Register GET");
        model.addAttribute("adminRegistrationForm", adminRegistrationForm);
        return "register/registerAdmin";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.POST)
     * Registers a new {@link Admin} for the data provided by the given {@link AdminRegistrationForm}
     * An interface is used to bind request parameters
     *
     * @param model                 the Spring MVC {@link Model}
     * @param adminRegistrationForm instance of {@link AdminRegistrationForm}
     * @param bindingResult         the result of the binding operation
     * @return redirect:/admin
     */
    @PostMapping("/register/registerAdmin")
    public String processRegistration(Model model, @ModelAttribute("adminRegistrationForm") @Valid AdminRegistrationForm adminRegistrationForm, BindingResult bindingResult){
        registrationFormValidator.validate(adminRegistrationForm, bindingResult);
        model.addAttribute("adminRegistrationForm", adminRegistrationForm);

        if (bindingResult.hasErrors()) {
            return "register/registerAdmin";
        }

        log.info("Log -- Admin Register POST");
        Admin admin = adminRegistrationForm.toAdmin(passwordEncoder);
        log.info("Admin: " + admin);
        try {
            adminRepository.save(admin);
        } catch (Exception e) {
            log.info("Errors: " + e.toString());
            return "register/registerAdmin";
        }

        securityService.autoLogin(adminRegistrationForm.getUsername(), adminRegistrationForm.getConfirmPassword());
        return "redirect:/admin";
    }
}



