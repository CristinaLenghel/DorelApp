package ro.sci.gr14.security;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.sci.gr14.model.*;

import java.util.*;

@Data
@Slf4j
public class HandymanRegistrationForm extends RegistrationForm {
    private String specialtyname;
    private Integer workingsince;
    private int priceperhour;
    //private String role;

    public Handyman toHandyman(PasswordEncoder passwordEncoder) {
        log.info("Registration Form - Create new Handyman account");
        Specialty specialty=new Specialty(specialtyname,workingsince,priceperhour);
        Handyman user= new Handyman(0L, super.getUsername(), passwordEncoder.encode(super.getPassword()),
                super.getEmail(), super.getFullname(), super.getPhonenumber(),
                super.getCity(), super.getCounty(), 1, new HashSet<>(),new TreeSet<>());
        log.info("Handyman created");
        user.addSpecialty(specialty);
        createEmptySchedule(user);
        return user;
    }

    public void createEmptySchedule(Handyman handyman){
        handyman.createEmptySchedulesSet();
    }
}
