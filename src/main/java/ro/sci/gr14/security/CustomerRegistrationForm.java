package ro.sci.gr14.security;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.sci.gr14.model.Customer;

/**
 * This class associates POJO attributes, beans and password encoding for {@link Customer} instances
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
public class CustomerRegistrationForm extends RegistrationForm {
    private String address;

    /**
     * Password encoding
     *
     * @param passwordEncoder the Springframework Security {@link PasswordEncoder}
     * @return customer
     */
    public Customer toCustomer(PasswordEncoder passwordEncoder){
        log.info("Customer Registration Form - Create new Customer account");
        Customer customer = new Customer(0L, super.getUsername(), passwordEncoder.encode(super.getPassword()),
                super.getEmail(), super.getFullname(), super.getPhonenumber(),
                super.getCity(), super.getCounty(), address, 2);
        log.info("Customer created" + customer);
        return customer;
    }

}
