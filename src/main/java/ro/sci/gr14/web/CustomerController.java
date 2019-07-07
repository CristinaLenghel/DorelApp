package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.data.ICustomerRepository;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.security.HandymanRegistrationForm;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping
public class CustomerController {

    @Autowired
    private ICustomerRepository customerRepo;

    @Autowired
    private IHandymanRepository handymanRepo;

    @Autowired
    public CustomerController(ICustomerRepository customerRepo){
        this.customerRepo=customerRepo;
    }

    @GetMapping("/customer")
    public String RegisterForm(Model model, Principal principal) {
        log.info("GET -  customerHomePage");
        String username = principal.getName();
        BaseUser user = customerRepo.findByUsername(username);
        model.addAttribute("myUser", user);
        model.addAttribute("handymans",handymanRepo.findByRole(1));
        return "customer/customerPage";
    }

    @RequestMapping("/customer/customerPage")
    public String processRegistration(Model model, Principal principal, @RequestParam String county) {
        String username = principal.getName();
        BaseUser user = customerRepo.findByUsername(username);
        model.addAttribute("myUser", user);
        model.addAttribute("handymans",handymanRepo.findByCounty(county));
        return "customer/customerPage";
    }
}