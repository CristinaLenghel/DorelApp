package ro.sci.gr14.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import ro.sci.gr14.model.Handyman;
import ro.sci.gr14.model.Rating;
import ro.sci.gr14.model.Schedule;

import java.util.Date;

@Data
@Slf4j
public class RegistrationFormHandyman {
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String phonenumber;
    private String city;
    private String county;
    //private String role;
    private String specialty;
    private String workingsince;
    private int priceperhour;


    public Handyman toUserHandyman(PasswordEncoder passwordEncoder) {
        log.info("Registration Form - Create new Handyman account");
        Handyman handyman = new Handyman(0L,username, passwordEncoder.encode(password),email,fullname, phonenumber, city, county,"handyman", specialty, workingsince, priceperhour);
        log.info("New user: "+ handyman);
        return handyman;
    }

}
