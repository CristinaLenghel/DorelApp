package ro.sci.gr14.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.data.ICustomerRepository;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.model.Customer;
import ro.sci.gr14.security.CustomerRegistrationForm;
import ro.sci.gr14.security.RegistrationForm;

@Controller
@RequestMapping("/register/registerCustomer")
@Slf4j

public class CustomerRegistrationController {

    private ICustomerRepository customerRepo;
    private PasswordEncoder passwordEncoder;

    public CustomerRegistrationController(
            ICustomerRepository customerRepo, PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        log.info("Log -- Customer Register GET");
        return "/register/registerCustomer";
    }

    @PostMapping
    public String processRegistration(CustomerRegistrationForm form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            log.info("Errors" +errors.toString());
            return "registerCustomer";
        }
        log.info("Log -- Customer Register POST");
        Customer customer = form.toCustomer(passwordEncoder);
        log.info("Customer: " +customer);
        try {
           customerRepo.save(customer);
        }
        catch (Exception e){
            log.info("Errors: " + e.toString());
            model.addAttribute("exception",e);
            model.addAttribute("myUser",customer);
            return "/register/registerCustomer";
        }
        return "redirect:/login";
    }
}