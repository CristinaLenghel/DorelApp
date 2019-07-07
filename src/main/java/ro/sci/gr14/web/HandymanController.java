package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.data.IScheduleRepository;
import ro.sci.gr14.data.ISpecialtyRepository;
import ro.sci.gr14.model.*;

import javax.jws.WebParam;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
    @Autowired
    public HandymanController(IBaseUserRepository useRepo){
        this.handymanRepo=handymanRepo;
    }

    @GetMapping
    public String handymanPage(Model model, Principal principal) {
        log.info("GET -  handymanHomePage");
        String username = principal.getName();
        Handyman handyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", handyman);
        return "handyman/handymanPage";
    }

    @GetMapping("/schedule")
    public String schedulePage(Model model, Principal principal) {
        log.info("GET -  schedule");
        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("form", new SchedulesCreation(myHandyman.getSchedules()));
        return "handyman/schedule";
    }

    @PostMapping("/schedule")
    public String saveSchedule(@ModelAttribute SchedulesCreation form, Model model, Principal principal){
        log.info("POST -  schedule");
        log.info("Post handyman:"+form.getSchedules());

        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", myHandyman);
        form.getSchedules().forEach(s->{s.setHandyman(myHandyman); scheduleRepo.save(s);});
        return "redirect:/handyman";
    }

    @GetMapping("/mySpecialties")
    public String mySpecialties(Model model, Principal principal) {
        log.info("GET -  mySpecialties");
        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", myHandyman);
        model.addAttribute("mySpecialties", myHandyman.getSpecialties());
        return "handyman/mySpecialties";
    }

    @GetMapping(value = "/editSpecialty/{id}")
    public String updateStudent(@PathVariable("id") long id, Model model, Principal principal)  {
        log.info("Get -  Edit Specialty, id = "+id);
        Specialty mySpecialty=specialtyRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid specialty Id:" + id));
        model.addAttribute("mySpecialty", mySpecialty);
        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", myHandyman);
        return "handyman/updateSpecialty";
    }

    @GetMapping(value = "/deleteSpecialty/{id}")
    public String deleteSpecialty(@PathVariable("id") long id, Model model, Principal principal) {
        try {
            log.info("Get -  Delete Specialty, id: " + id);

            Specialty mySpecialty = specialtyRepo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid specialty Id:" + id));
            specialtyRepo.delete(mySpecialty);
            String username = principal.getName();
            Handyman myHandyman = handymanRepo.findByUsername(username);
            model.addAttribute("myHandyman", myHandyman);
            model.addAttribute("mySpecialties", myHandyman.getSpecialties());
            return "redirect:/handyman/mySpecialties";
        } catch(Exception e) {
            return "redirect:/errors";
        }
    }

    @GetMapping("/updateSpecialty")
    public String updateSpecialty(Model model, Principal principal, Specialty mySpecialty) {
        log.info("GET -  addSpecialty");
        String username = principal.getName();
        Handyman handyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", handyman);
        return "handyman/updateSpecialty";
    }

    @PostMapping("/updateSpecialty/{id}")
    public String saveSpecialty(@PathVariable("id") long id, Model model, Principal principal, Specialty mySpecialty) {
        String username = principal.getName();
        Handyman myHandyman = handymanRepo.findByUsername(username);
        model.addAttribute("myHandyman", myHandyman);
        mySpecialty.setId(id);
        mySpecialty.setHandyman(myHandyman);
        try {
            specialtyRepo.save(mySpecialty);
            log.info("New Specialty added:"+ mySpecialty);
        }
        catch (Exception e) {
            return "redirect:/errors";
        }

        model.addAttribute("mySpecialties", myHandyman.getSpecialties());
        return "redirect:/handyman/mySpecialties";
    }

}