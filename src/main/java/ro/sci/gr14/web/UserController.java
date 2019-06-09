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
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.model.BaseUser;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IBaseUserRepository userRepo;

    @Autowired
    public  UserController(IBaseUserRepository useRepo){ this.userRepo=userRepo;
    }


    @GetMapping
    public String registerUser(Model model, Principal principal) {
        log.info("log -- user getMapping");
        String username = principal.getName();
        log.info("log -- username: "+username);
        BaseUser user = userRepo.findByUsername(username);
        log.info("log -- "+user.toString());
        model.addAttribute("myUser", user);
        return "user";
    }
}
