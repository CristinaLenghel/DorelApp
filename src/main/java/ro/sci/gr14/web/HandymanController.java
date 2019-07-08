package ro.sci.gr14.web;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.data.IScheduleRepository;
import ro.sci.gr14.data.ISpecialtyRepository;
import ro.sci.gr14.model.Handyman;
import ro.sci.gr14.model.SchedulesCreation;
import ro.sci.gr14.model.Specialty;

import java.security.Principal;

/**
 * Controller implementation for {@link Handyman} instances
 * All handling methods on this controller are relative to the /handyman path
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
@RequestMapping("/handyman")
public class HandymanController {
    @Autowired
    private IHandymanRepository handymanRepo;

    @Autowired
    private ISpecialtyRepository specialtyRepo;

    @Autowired
    private IScheduleRepository scheduleRepo;

    /**
     * Autowired constructor
     *
     * @param useRepo autowired byType
     */
    @Autowired
    public HandymanController(IBaseUserRepository useRepo){
        this.handymanRepo = handymanRepo;
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     * An HTTP GET for /handyman invokes this method
     *
     * @param model     the Spring MVC {@link Model}
     * @param principal the Java Security {@link Principal}
     * @return handyman/handymanPage
     */
    @GetMapping
    public String handymanPage(Model model, Principal principal){
        log.info("GET -  handymanHomePage");
        String username = principal.getName();
        Handyman handyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", handyman);
        return "handyman/handymanPage";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param model     the Spring MVC {@link Model}
     * @param principal the Java Security {@link Principal}
     * @return handyman/schedule
     */
    @GetMapping("/schedule")
    public String schedulePage(Model model, Principal principal){
        log.info("GET -  schedule");
        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("form", new SchedulesCreation(myHandyman.getSchedules()));
        return "handyman/schedule";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.POST)
     *
     * @param form      an instance of {@link SchedulesCreation} class
     * @param model     the Spring MVC {@link Model}
     * @param principal the Java Security {@link Principal}
     * @return redirect:/handyman
     */
    @PostMapping("/schedule")
    public String saveSchedule(@ModelAttribute SchedulesCreation form, Model model, Principal principal){
        log.info("POST -  schedule");
        log.info("Post handyman:" + form.getSchedules());

        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", myHandyman);
        form.getSchedules().forEach(s -> {
            s.setHandyman(myHandyman);
            scheduleRepo.save(s);
        });
        return "redirect:/handyman";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param model     the Spring MVC {@link Model}
     * @param principal the Java Security {@link Principal}
     * @return handyman/mySpecialties
     */
    @GetMapping("/mySpecialties")
    public String mySpecialties(Model model, Principal principal){
        log.info("GET -  mySpecialties");
        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", myHandyman);
        model.addAttribute("mySpecialties", myHandyman.getSpecialties());
        return "handyman/mySpecialties";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param id        an int containing the id of {@link Handyman} instance
     * @param model     the Spring MVC {@link Model}
     * @param principal principal the Java Security {@link Principal}
     * @return handyman/updateSpecialty
     */
    @GetMapping(value = "/editSpecialty/{id}")
    public String updateSpecialty(@PathVariable("id") long id, Model model, Principal principal){
        log.info("Get -  Edit Specialty, id = " + id);
        Specialty mySpecialty = specialtyRepo.findById(id)
                .orElseThrow(( ) -> new IllegalArgumentException("Invalid specialty Id:" + id));
        model.addAttribute("mySpecialty", mySpecialty);
        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", myHandyman);
        return "handyman/updateSpecialty";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param id        an int containing the id of {@link Handyman} instance
     * @param model     the Spring MVC {@link Model}
     * @param principal principal the Java Security {@link Principal}
     * @return redirect:/handyman/mySpecialties
     */
    @GetMapping(value = "/deleteSpecialty/{id}")
    public String deleteSpecialty(@PathVariable("id") long id, Model model, Principal principal){
        try {
            log.info("Get -  Delete Specialty, id: " + id);

            Specialty mySpecialty = specialtyRepo.findById(id)
                    .orElseThrow(( ) -> new IllegalArgumentException("Invalid specialty Id:" + id));
            specialtyRepo.delete(mySpecialty);
            String username = principal.getName();
            Handyman myHandyman = handymanRepo.findByUsername(username);
            model.addAttribute("myHandyman", myHandyman);
            model.addAttribute("mySpecialties", myHandyman.getSpecialties());
            return "redirect:/handyman/mySpecialties";
        } catch (Exception e) {
            return "redirect:/errors";
        }
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @param model       the Spring MVC {@link Model}
     * @param principal   the Java Security {@link Principal}
     * @param mySpecialty an instance of {@Specialty} class
     * @return handyman/updateSpecialty
     */
    @GetMapping("/updateSpecialty")
    public String updateSpecialty(Model model, Principal principal, Specialty mySpecialty){
        log.info("GET -  addSpecialty");
        String username = principal.getName();
        Handyman handyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", handyman);
        return "handyman/updateSpecialty";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.POST)
     *
     * @param id          an int containing the id of {@link Handyman} instance
     * @param model       the Spring MVC {@link Model}
     * @param principal   principal the Java Security {@link Principal}
     * @param mySpecialty an instance of {@Specialty} class
     * @return redirect:/handyman/mySpecialties
     */
    @PostMapping("/updateSpecialty/{id}")
    public String saveSpecialty(@PathVariable("id") long id, Model model, Principal principal, Specialty mySpecialty){
        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", myHandyman);
        mySpecialty.setId(id);
        mySpecialty.setHandyman(myHandyman);
        try {
            specialtyRepo.save(mySpecialty);
            log.info("New Specialty added:" + mySpecialty);
        } catch (Exception e) {
            return "redirect:/errors";
        }

        model.addAttribute("mySpecialties", myHandyman.getSpecialties());
        return "redirect:/handyman/mySpecialties";
    }

}