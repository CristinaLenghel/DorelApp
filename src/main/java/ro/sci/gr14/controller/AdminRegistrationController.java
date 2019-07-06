package ro.sci.gr14.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.IAdminRepository;
import ro.sci.gr14.model.Admin;
import ro.sci.gr14.model.RegistrationFormValidator;
import ro.sci.gr14.security.AdminRegistrationForm;
import ro.sci.gr14.security.SecurityService;

import javax.validation.Valid;

@Controller
//@RequestMapping("/register/registerAdmin")
@Slf4j
public class AdminRegistrationController {

    private IAdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationFormValidator registrationFormValidator;

    @Autowired
    private SecurityService securityService;

    public AdminRegistrationController(
            IAdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register/registerAdmin")
    public String registerForm(Model model, AdminRegistrationForm adminRegistrationForm) {
        log.info("Log -- Admin Register GET");
        model.addAttribute("adminRegistrationForm", adminRegistrationForm);
        return "register/registerAdmin";
    }

    @PostMapping("/register/registerAdmin")
    public String processRegistration(Model model, @ModelAttribute("adminRegistrationForm") @Valid AdminRegistrationForm adminRegistrationForm, BindingResult bindingResult){
        registrationFormValidator.validate(adminRegistrationForm, bindingResult);
        model.addAttribute("adminRegistrationForm", adminRegistrationForm);

        if (bindingResult.hasErrors()) {
            return "register/registerAdmin";
        }

        log.info("Log -- Admin Register POST");
        Admin admin = adminRegistrationForm.toAdmin(passwordEncoder);
        log.info("Admin: " +admin);
        try {
            adminRepository.save(admin);
        }
        catch (Exception e){
            log.info("Errors: " + e.toString());
            return "register/registerAdmin";
        }

        securityService.autoLogin(adminRegistrationForm.getUsername(), adminRegistrationForm.getConfirmPassword());
        return "redirect:/admin";
    }
}



