package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.ApplicationRepository;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.model.BaseUser;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
public class UserController {
    @Autowired
    ApplicationRepository appRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("counties", appRepo.findAllCounty());
        return "test";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
}