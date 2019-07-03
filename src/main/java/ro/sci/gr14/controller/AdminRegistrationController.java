package ro.sci.gr14.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.IAdminRepository;
import ro.sci.gr14.model.Admin;
import ro.sci.gr14.security.AdminRegistrationForm;

@Controller
@RequestMapping("/register/registerAdmin")
@Slf4j
public class AdminRegistrationController {

    private IAdminRepository adminRepo;
    private PasswordEncoder passwordEncoder;

    public AdminRegistrationController(
            IAdminRepository adminRepo, PasswordEncoder passwordEncoder) {
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        log.info("Log -- Admin Register GET");
        return "/register/registerAdmin";
    }

    @PostMapping
    public String processRegistration(AdminRegistrationForm form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            log.info("Errors" +errors.toString());
            return "registerAdmin";
        }
        log.info("Log -- Admin Register POST");
        Admin admin = form.toAdmin(passwordEncoder);
        log.info("Admin: " +admin);
        try {
            adminRepo.save(admin);
        }
        catch (Exception e){
            log.info("Errors: " + e.toString());
            model.addAttribute("exception",e);
            model.addAttribute("myAdmin",admin);
            return "/register/registerAdmin";
        }
        return "redirect:/login";
    }
}


