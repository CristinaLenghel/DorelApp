package ro.sci.gr14.security;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.sci.gr14.model.Admin;


@Data
@Slf4j
public class AdminRegistrationForm extends RegistrationForm {

    public Admin toAdmin(PasswordEncoder passwordEncoder) {
        log.info("Admin Registration Form - Create new Admin account");
        Admin admin= new Admin(0L, super.getUsername(), passwordEncoder.encode(super.getPassword()),
                super.getEmail(), super.getFullname(), super.getPhonenumber(),
                super.getCity(), super.getCounty(),0);
        log.info("Admin created" +admin);
        return admin;
    }
}
