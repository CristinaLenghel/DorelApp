package ro.sci.gr14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.sci.gr14.data.ApplicationRepository;
import ro.sci.gr14.model.City;
import ro.sci.gr14.model.County;
import ro.sci.gr14.model.DBEntity;

import java.util.List;

@RestController
public class ApplicationRestController {

    @Autowired
    private ApplicationRepository appRepo;

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
