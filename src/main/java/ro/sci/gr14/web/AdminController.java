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
import ro.sci.gr14.model.Specialty;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminRepository adminRepo;

    @Autowired
    private IHandymanRepository handymanRepository;

    @Autowired
    public AdminController(IAdminRepository adminRepo){
        this.adminRepo=adminRepo;
    }

    @GetMapping
    public String handymanPage(Model model, Principal principal) {
        log.info("GET -  adminHomePage");
        String username = principal.getName();
        BaseUser admin = adminRepo.findByUsername(username);
        model.addAttribute("myAdmin", admin);
        return "admin/adminPage";
    }

    @GetMapping("/myHandymans")
    public String mySpecialties(Model model, Principal principal) {
        log.info("GET -  myHandymans");
        String username = principal.getName();
        Admin myAdmin = adminRepo.findByUsername(username);
        model.addAttribute("myAdmin", myAdmin);
        model.addAttribute("myHandymans", handymanRepository.findAll());
        return "admin/myHandymans";
    }

    @GetMapping(value = "/deleteSpecialty/{id}")
    public String deleteSpecialty(@PathVariable("id") long id, Model model, Principal principal) {
        log.info("Get -  Delete Handyman, id: "+id);
        Handyman myHandyman=handymanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid handyman Id:" + id));
        handymanRepository.delete(myHandyman);
        String username = principal.getName();
        Admin myAdmin = adminRepo.findByUsername(username);
        model.addAttribute("myAdmin", myAdmin);
        model.addAttribute("mySpecialties", handymanRepository.findAll());
        return "redirect:/admin/myHandymans";
    }
}

