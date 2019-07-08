package ro.sci.gr14.security;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.sci.gr14.model.Handyman;
import ro.sci.gr14.model.Schedule;
import ro.sci.gr14.model.Specialty;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * This class associates POJO attributes, beans and password encoding for {@link Handyman} instances
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Data
@Slf4j
public class HandymanRegistrationForm extends RegistrationForm {
    private String specialtyname;
    private Integer workingsince;
    private int priceperhour;

    /**
     * Password encoding
     *
     * @param passwordEncoder the Springframework Security {@link PasswordEncoder}
     * @return user
     */
    public Handyman toHandyman(PasswordEncoder passwordEncoder){
        log.info("Registration Form - Create new Handyman account");
        Specialty specialty = new Specialty(specialtyname, workingsince, priceperhour);
        Handyman user = new Handyman(0L, super.getUsername(), passwordEncoder.encode(super.getPassword()),
                super.getEmail(), super.getFullname(), super.getPhonenumber(),
                super.getCity(), super.getCounty(), 1, new HashSet<>(), new TreeSet<>());
        log.info("Handyman created");
        user.addSpecialty(specialty);
        createEmptySchedule(user);
        return user;
    }

    /**
     * Creates empty Sets for {@link Schedule} objects for every {@link Handyman} instance
     *
     * @param handyman
     */
    public void createEmptySchedule(Handyman handyman){
        handyman.createEmptySchedulesSet();
    }
}
