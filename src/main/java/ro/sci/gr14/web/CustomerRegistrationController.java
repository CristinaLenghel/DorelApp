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
import ro.sci.gr14.data.ICustomerRepository;
import ro.sci.gr14.model.Customer;
import ro.sci.gr14.model.RegistrationFormValidator;
import ro.sci.gr14.security.CustomerRegistrationForm;
import ro.sci.gr14.security.SecurityService;

import javax.validation.Valid;

/**
 * Controller implementation for {@link Customer} instances registration
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
public class CustomerRegistrationController {

    private ICustomerRepository customerRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationFormValidator registrationFormValidator;

    @Autowired
    private SecurityService securityService;

    /**
     * Constructor taking two parameters
     *
     * @param customerRepo    an instance of {@link ICustomerRepository}
     * @param passwordEncoder the Springframework Security {@link PasswordEncoder}
     */
    public CustomerRegistrationController(
            ICustomerRepository customerRepo, PasswordEncoder passwordEncoder){
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param model                the Spring MVC {@link Model}
     * @param customerRegisterForm instance of {@link CustomerRegistrationForm}
     * @return register/registerCustomer
     */
    @GetMapping("/register/registerCustomer")
    public String registerForm(Model model, CustomerRegistrationForm customerRegisterForm){
        log.info("Log -- Customer Register GET");
        model.addAttribute("customerRegisterForm", customerRegisterForm);
        return "register/registerCustomer";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.POST)
     * Registers a new {@link Customer} for the data provided by the given {@link CustomerRegistrationForm}
     * An interface is used to bind request parameters
     *
     * @param model                the Spring MVC {@link Model}
     * @param customerRegisterForm instance of {@link CustomerRegistrationForm}
     * @param bindingResult        the result of the binding operation
     * @return redirect:/customer
     */
    @PostMapping("/register/registerCustomer")
    public String processRegistration(Model model, @ModelAttribute("customerRegisterForm") @Valid CustomerRegistrationForm customerRegisterForm, BindingResult bindingResult){
        registrationFormValidator.validate(customerRegisterForm, bindingResult);
        model.addAttribute("customerRegiterForm", customerRegisterForm);

        if (bindingResult.hasErrors()) {
            return "register/registerCustomer";
        }

        log.info("Log -- Customer Register POST");
        Customer customer = customerRegisterForm.toCustomer(passwordEncoder);
        log.info("Customer: " + customer);
        try {
            customerRepo.save(customer);
        } catch (Exception e) {
            log.info("Errors: " + e.toString());
            return "register/registerCustomer";
        }

        securityService.autoLogin(customerRegisterForm.getUsername(), customerRegisterForm.getConfirmPassword());
        return "redirect:/customer";
    }
}