package ro.sci.gr14.web;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

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

/**
 * Controller implementation for {@link Admin} instances
 * All handling methods for this @Controller annotated class are relative to the /admin path
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminRepository adminRepo;

    @Autowired
    private IHandymanRepository handymanRepository;

    /**
     * Autowired constructor
     *
     * @param adminRepo autowired byType
     */
    @Autowired
    public AdminController(IAdminRepository adminRepo){
        this.adminRepo = adminRepo;
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param model     the Spring MVC {@link Model}
     * @param principal the Java Security {@link Principal}
     * @return admin/adminPage
     */
    @GetMapping
    public String handymanPage(Model model, Principal principal){
        log.info("GET -  adminHomePage");
        String username = principal.getName();
        BaseUser admin = adminRepo.findByUsername(username);
        model.addAttribute("myAdmin", admin);
        return "admin/adminPage";
    }

    /**
     * Composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param model     the Spring MVC {@link Model}
     * @param principal the Java Security {@link Principal}
     * @return admin/myHandymans
     */
    @GetMapping("/myHandymans")
    public String myHandymans(Model model, Principal principal){
        log.info("GET -  myHandymans");
        String username = principal.getName();
        Admin myAdmin = adminRepo.findByUsername(username);
        model.addAttribute("myAdmin", myAdmin);
        model.addAttribute("myHandymans", handymanRepository.findAll());
        return "admin/myHandymans";
    }

    /**
     * Composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     * Deletes the {@link Handyman} instance with the id of the submitted value
     *
     * @param id        an int containing the id of {@link Handyman} instance
     * @param model     the Spring MVC {@link Model}
     * @param principal principal the Java Security {@link Principal}
     * @return redirect:/admin/myHandymans
     */
    @GetMapping(value = "/deleteHandyman/{id}")
    public String deleteHandyman(@PathVariable("id") long id, Model model, Principal principal){
        log.info("Get -  Delete Handyman, id: " + id);
        Handyman myHandyman = handymanRepository.findById(id)
                .orElseThrow(( ) -> new IllegalArgumentException("Invalid handyman Id:" + id));
        handymanRepository.delete(myHandyman);
        String username = principal.getName();
        Admin myAdmin = adminRepo.findByUsername(username);
        model.addAttribute("myAdmin", myAdmin);
        model.addAttribute("myHandymans", handymanRepository.findAll());
        return "redirect:/admin/myHandymans";
    }
}

