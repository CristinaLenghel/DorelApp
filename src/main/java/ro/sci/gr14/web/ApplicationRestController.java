package ro.sci.gr14.web;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sci.gr14.data.ApplicationRepository;
import ro.sci.gr14.model.City;
import ro.sci.gr14.model.County;
import ro.sci.gr14.model.DBEntity;

import java.util.List;

/**
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@RestController
public class ApplicationRestController {

    @Autowired
    private ApplicationRepository appRepo;

    /**
     *
     * @param countyname
     * @return
     */
    @RequestMapping(path="/jsondata/cities")
    public List<City> getAllCities(@RequestParam(required = false) String countyname){
        if (countyname!=null)
            return appRepo.findAllCityByCounty(countyname);
        else
            return appRepo.findAllCity();
    }

    @RequestMapping(path="/jsondata/counties")
    public List<County> getAllCounty(){
        return appRepo.findAllCounty();
    }

    @RequestMapping(path="/jsondata/handicrafts")
    public List<DBEntity> getAllHandicraft(){
        return appRepo.findAllHandicraft();
    }
}
