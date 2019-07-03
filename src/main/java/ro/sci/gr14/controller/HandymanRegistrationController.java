package ro.sci.gr14.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.model.Handyman;
import ro.sci.gr14.security.HandymanRegistrationForm;

@Controller
@RequestMapping("/registerHandyman")
@Slf4j
public class HandymanRegistrationController {

    private IHandymanRepository handymanRepo;
    //private ISpecialtyRepository specialtyRepo;
    private PasswordEncoder passwordEncoder;

//    public HandymanRegistrationController(
//            IBaseUserRepository userRepo, PasswordEncoder passwordEncoder) {
//        this.userRepo = userRepo;
//        this.passwordEncoder = passwordEncoder;
//    }

    public HandymanRegistrationController(
            IHandymanRepository handymanRepo, PasswordEncoder passwordEncoder) {
        this.handymanRepo = handymanRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String RegisterForm() {
        log.info("Log -- Handyman Register Get");
        return "registerHandyman";
    }

    @PostMapping
    public String processRegistration(HandymanRegistrationForm form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            log.info("Errors" +errors.toString());
            return "registerHandyman";
        }
        log.info("Log -- Handyman Register post");
        Handyman handyman = form.toHandyman(passwordEncoder);
        log.info("form.toHandyman -- "+ handyman);
        try {
            handymanRepo.save(handyman);
            //userRepo.save(specialty.getBase_user());
        }
        catch (Exception e){
            log.info("Errors" +e.toString());
            model.addAttribute("exception",e);
            //model.addAttribute("baseUser",handymanUser);
            return "registerHandyman";
        }
        return "redirect:/login";
    }

}