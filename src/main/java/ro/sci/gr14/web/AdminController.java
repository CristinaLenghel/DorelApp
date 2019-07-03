package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.sci.gr14.data.IAdminRepository;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.model.Admin;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.model.Handyman;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminRepository adminRepo;

    @Autowired
    private IHandymanRepository handymanRepo;

    @Autowired
    public AdminController(IAdminRepository adminRepo){
        this.adminRepo=adminRepo;
    }

    @GetMapping
    public String adminPage(Model model, Principal principal) {
        log.info("GET -  adminHomePage");
        String username = principal.getName();
        BaseUser admin = adminRepo.findByUsername(username);
        model.addAttribute("myAdmin", admin);
        return "admin/adminPage";
    }

}

