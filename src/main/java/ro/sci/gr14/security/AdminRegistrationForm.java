package ro.sci.gr14.security;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.sci.gr14.model.Admin;

/**
 * This class associates POJO attributes, beans and password encoding for {@link Admin instances
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
public class AdminRegistrationForm extends RegistrationForm {

    /**
     * Password encoding
     *
     * @param passwordEncoder the Springframework Security {@link PasswordEncoder}
     * @return admin
     */
    public Admin toAdmin(PasswordEncoder passwordEncoder){
        log.info("Admin Registration Form - Create new Admin account");
        Admin admin = new Admin(0L, super.getUsername(), passwordEncoder.encode(super.getPassword()),
                super.getEmail(), super.getFullname(), super.getPhonenumber(),
                super.getCity(), super.getCounty(), 0);
        log.info("Admin created" + admin);
        return admin;
    }
}
