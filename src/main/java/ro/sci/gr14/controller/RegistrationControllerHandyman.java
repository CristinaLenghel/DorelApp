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
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.model.Handyman;
import ro.sci.gr14.security.RegistrationFormHandyman;

@Controller
@RequestMapping("/registerHandyman")
@Slf4j
public class RegistrationControllerHandyman {



        private IHandymanRepository handymanRepo;
        private PasswordEncoder passwordEncoder;

        public RegistrationControllerHandyman(
                IHandymanRepository handymanRepoRepo, PasswordEncoder passwordEncoder) {
            this.handymanRepo = handymanRepoRepo;
            this.passwordEncoder = passwordEncoder;
        }

        @GetMapping
        public String registerForm() {
            log.info("Log -- Register GetMapping");
            return "registerHandyman";
        }

        @PostMapping
        public String processRegistrationHandyman(RegistrationFormHandyman form, Errors errors, Model model) {
            if (errors.hasErrors()) {
                log.info("Errors" + errors.toString());
                return "/registerHandyman";
            }
            log.info("Log -- Register post");
            Handyman handyman = form.toUserHandyman(passwordEncoder);
            try {
                handymanRepo.save(handyman);
            } catch (Exception e) {
                log.info("Errors" + e.toString());
                model.addAttribute("exception", e);
                model.addAttribute("handyman", handyman);
                return "/registerHandyman";
            }
            return "redirect:/login";
        }
    }

