package ro.sci.gr14.web;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.sci.gr14.data.ApplicationRepository;

/**
 * Controller implementation for events occurring during authentication
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
public class UserController {
    @Autowired
    ApplicationRepository appRepo;

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @return login
     */
    @GetMapping("/login")
    public String login( ){
        return "login";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @return test
     */
    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("counties", appRepo.findAllCounty());
        return "test";
    }

    /**
     * The composed annotation acts as a shortcut for @RequestMapping(method = RequestMethod.GET)
     *
     * @return /error/access-denied
     */
    @GetMapping("/access-denied")
    public String accessDenied( ){
        return "/error/access-denied";
    }
}