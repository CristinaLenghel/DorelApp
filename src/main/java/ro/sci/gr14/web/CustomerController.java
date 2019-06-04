package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.model.BaseUser;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    IBaseUserRepository buRepo;


    @GetMapping
    public String saveUser(Model model) {
//        BaseUser baseUser=new BaseUser();
//        baseUser.setUserName("userName");
//
//        buRepo.save(baseUser);

        BaseUser baseUser=buRepo.findById(1L).orElse(new BaseUser());
        model.addAttribute("myUser",baseUser);
        System.out.println(baseUser);

//                .ifPresent(bs -> {
//                    log.info("Customer found with findById(1L):");
//                    log.info("--------------------------------");
//                    log.info(bs.toString());
//                    log.info("");
//                });

        return "user";
    }
}
