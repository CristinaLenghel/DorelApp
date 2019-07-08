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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerRepository customerRepo;

    @Autowired
    private IHandymanRepository handymanRepo;

    @Autowired
    public CustomerController(ICustomerRepository customerRepo){
        this.customerRepo=customerRepo;
    }

    @GetMapping
    public String customerPage(Model model, Principal principal) {
        log.info("GET -  customerHomePage");
        String username = principal.getName();
        BaseUser user = customerRepo.findByUsername(username);
        model.addAttribute("myUser", user);
        return "customer/customerPage";
    }

    @GetMapping("/findHandyman")
    public String findHandyman(Model model, Principal principal, @RequestParam(required = false) String county, @RequestParam(required = false) String specialtyname, @RequestParam(required = false) String city) {
        String username = principal.getName();
        BaseUser user = customerRepo.findByUsername(username);
        model.addAttribute("myUser", user);
        if (county==null) county="";
        if (specialtyname==null) specialtyname="";
        if (city==null) city="";
        //model.addAttribute("handymans",handymanRepo.findByCounty(county));
        model.addAttribute("county",county);
        model.addAttribute("city",city);
        model.addAttribute("specialtyname",specialtyname);
        model.addAttribute("handymans",handymanRepo.findByCountyAndCityAndSpecialty(county, city,specialtyname));
        return "customer/findHandyman";
    }
}