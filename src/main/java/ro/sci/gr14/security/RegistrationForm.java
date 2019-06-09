package ro.sci.gr14.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import ro.sci.gr14.model.BaseUser;

@Data
@Slf4j
public class RegistrationForm {
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String phonenumber;
    private String city;
    private String county;
    //private String role;

    public BaseUser toUser(PasswordEncoder passwordEncoder) {
        log.info("Registration Form - Create new Base user");
        BaseUser user= new BaseUser(0L,username, passwordEncoder.encode(password),email,fullname, phonenumber, city, county, "user");
        log.info("New user: "+user);
        return user;
    }

}
