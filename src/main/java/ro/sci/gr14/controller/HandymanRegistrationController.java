package ro.sci.gr14.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.data.ISpecialtyRepository;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.model.Handyman;
import ro.sci.gr14.model.RegistrationFormValidator;
import ro.sci.gr14.model.Specialty;
import ro.sci.gr14.security.CustomerRegistrationForm;
import ro.sci.gr14.security.HandymanRegistrationForm;
import ro.sci.gr14.security.RegistrationForm;
import ro.sci.gr14.security.SecurityService;

import javax.validation.Valid;

@Controller
//@RequestMapping("/register/registerHandyman")
@Slf4j
public class HandymanRegistrationController {
    private IHandymanRepository handymanRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationFormValidator registrationFormValidator;
    @Autowired
    private SecurityService securityService;

    public HandymanRegistrationController(
            IHandymanRepository handymanRepo, PasswordEncoder passwordEncoder) {
        this.handymanRepo = handymanRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register/registerHandyman")
    public String RegisterForm(Model model, HandymanRegistrationForm handymanRegistrationForm) {
        log.info("Log -- Handyman Register Get");
        model.addAttribute("handymanRegisterForm", handymanRegistrationForm);
        return "register/registerHandyman";
    }

    @PostMapping("/register/registerHandyman")
    public String processRegistration(Model model, @ModelAttribute("handymanRegisterForm") @Valid HandymanRegistrationForm handymanRegisterForm, BindingResult bindingResult) {
        registrationFormValidator.validate(handymanRegisterForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register/registerHandyman";
        }

        log.info("Log -- Handyman Register post");
        Handyman handyman = handymanRegisterForm.toHandyman(passwordEncoder);
        log.info("form.toHandyman -- "+ handyman);
        try {
            handymanRepo.save(handyman);
        }
        catch (Exception e){
            log.info("Errors" +e.toString());
            return "register/registerHandyman";
        }

        securityService.autoLogin(handymanRegisterForm.getUsername(), handymanRegisterForm.getConfirmPassword());
        return "redirect:/handyman";
    }

}