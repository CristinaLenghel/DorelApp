package ro.sci.gr14.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import ro.sci.gr14.model.BaseUser;

import javax.validation.constraints.*;

@Data
@Slf4j
public class RegistrationForm {
    @Size(min=5, max=30, message = "Username sa fie mai mare de 5 caractere")
    private String username;
    @Size(min=5, max=30, message = "Parola sa fie mai mare de 5 caractere")
    private String password;
    private String confirmPassword;
    @Email(message = "Email invalid.")
    private String email;
    @NotEmpty(message = "*")
    private String fullname;
    @NotEmpty(message = "*")
    @Pattern(regexp = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$", message = "Telefon invalid.")
    private String phonenumber;
    @NotEmpty(message = "*")
    private String city;
    @NotEmpty(message = "*")
    private String county;
    //private String role;

    public BaseUser toUser(PasswordEncoder passwordEncoder) {
        log.info("Registration Form - Create new Base user");
        BaseUser user= new BaseUser(0L,username, passwordEncoder.encode(password),email,fullname, phonenumber, city, county,0);
        log.info("New user: "+user);
        return user;
    }

}
