package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.model.BaseUser;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@Controller
public class CustomerController {

//
//    @Autowired
//    IBaseUserRepository buRepo;
//
//
//    @GetMapping("/user")
//    public String saveUser(Model model) {
//       BaseUser baseUser=new BaseUser(1L,"laurentiu","steaua","marius@gmail.com",
//               "marius","012312","aiud","alba","sal");
//       buRepo.save(baseUser);
//        model.addAttribute("myUser", baseUser);
//
////        BaseUser baseUser=buRepo.findById(1L).orElse(new BaseUser());
////        model.addAttribute("myUser",baseUser);
////        System.out.println(baseUser);
//
////                .ifPresent(bs -> {
////                    log.info("Customer found with findById(1L):");
////                    log.info("--------------------------------");
////                    log.info(bs.toString());
////                    log.info("");
////                });
//
//        return "user";
//    }
//
//    @GetMapping("/register")
//    public String register(Model model){
//        return "register";
//    }
//
////    @PostMapping(@ModelAttribute BaseUser s){
////        buRepo.save(bs);
////    }

    @Autowired
    IBaseUserRepository buRepo;

    @GetMapping
    public String saveUser(Model model) {

        BaseUser baseUser=buRepo.findById(1L).orElse(new BaseUser());
        model.addAttribute("myUser",baseUser);
        System.out.println(baseUser);

        return "user";
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            buRepo.save(new BaseUser(12L, "laurentiu", "steaua", "marius@gmail.com", "marius", "012312", "aiud", "alba", "sal"));
        };
    }

}
