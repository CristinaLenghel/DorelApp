package ro.sci.gr14.security;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.model.Customer;
import ro.sci.gr14.model.Handyman;

import java.util.ArrayList;

@Data
@Slf4j
public class CustomerRegistrationForm extends RegistrationForm {
    private String address;

    public Customer toCustomer(PasswordEncoder passwordEncoder) {
        log.info("Customer Registration Form - Create new Customer account");
        Customer customer= new Customer(0L, super.getUsername(), passwordEncoder.encode(super.getPassword()),
                super.getEmail(), super.getFullname(), super.getPhonenumber(),
                super.getCity(), super.getCounty(), address,2);
        log.info("Customer created" +customer);
        return customer;
    }

}
