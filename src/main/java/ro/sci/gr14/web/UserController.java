package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.model.BaseUser;

@Slf4j
@Controller
public class UserController {
    @Autowired
    IBaseUserRepository buRepo;

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("baseUser", new BaseUser());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute BaseUser baseUser){
        System.out.println(baseUser.toString());
        buRepo.save(baseUser);
        return "user";
    }

}