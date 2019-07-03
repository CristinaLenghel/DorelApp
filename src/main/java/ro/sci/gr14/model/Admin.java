package ro.sci.gr14.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("0")
public class Admin extends BaseUser {

    public Admin(Long id, String userName, String password, String email, String fullName, String phoneNumber, String city, String county,Integer role) {
        super(id, userName, password, email, fullName, phoneNumber, city, county, role);
    }
}
