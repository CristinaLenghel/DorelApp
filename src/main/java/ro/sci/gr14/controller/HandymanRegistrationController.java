package ro.sci.gr14.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.data.ISpecialtyRepository;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.model.Handyman;
import ro.sci.gr14.model.Specialty;
import ro.sci.gr14.security.HandymanRegistrationForm;
import ro.sci.gr14.security.RegistrationForm;

@Controller
@RequestMapping("/register/registerHandyman")
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
        return "register/registerHandyman";
    }

    @PostMapping
    public String processRegistration(HandymanRegistrationForm form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            log.info("Errors" +errors.toString());
            return "register/registerHandyman";
        }
        log.info("Log -- Handyman Register post");
        Handyman handyman = form.toHandyman(passwordEncoder);
        log.info("form.toHandyman -- "+ handyman);
        try {
            Handyman registered=handymanRepo.findByUsername(handyman.getUsername());
            if (registered==null){
                log.info("HandymanControler - save handyman");
                log.info("Handyman: "+handyman);
                handymanRepo.save(handyman);}
            else {
                model.addAttribute("username", form.getUsername());
                //model.addAttribute("error","Username existent");
                //return  "register/registerHandyman";
                throw new Exception("Username existent.");
            }

        }
        catch (Exception e){
            log.info("Errors" +e.toString());
            model.addAttribute("exception",e);
            return "register/registerHandyman";
        }
        return "redirect:/login";
    }

}