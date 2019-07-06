package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.data.ICustomerRepository;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.model.BaseUser;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerRepository customerRepo;

    @Autowired
    public CustomerController(ICustomerRepository customerRepo){
        this.customerRepo=customerRepo;
    }

    @GetMapping
    public String handymanPage(Model model, Principal principal) {
        log.info("GET -  customerHomePage");
        String username = principal.getName();
        BaseUser user = customerRepo.findByUsername(username);
        model.addAttribute("myUser", user);
        return "customer/customerPage";
    }
}