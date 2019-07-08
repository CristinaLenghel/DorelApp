package ro.sci.gr14.security;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.sci.gr14.model.BaseUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * This class associates POJO attributes, beans and password encoding for the generic {@link BaseUser}
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
public class RegistrationForm {
    @Size(min = 5, max = 30, message = "Username sa fie mai mare de 5 caractere")
    private String username;
    @Size(min = 5, max = 30, message = "Parola sa fie mai mare de 5 caractere")
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

    /**
     * Password encoding
     *
     * @param passwordEncoder the Springframework Security {@link PasswordEncoder}
     * @return user
     */
    public BaseUser toUser(PasswordEncoder passwordEncoder){
        log.info("Registration Form - Create new Base user");
        BaseUser user = new BaseUser(0L, username, passwordEncoder.encode(password), email, fullname, phonenumber, city, county, 0);
        log.info("New user: " + user);
        return user;
    }

}
