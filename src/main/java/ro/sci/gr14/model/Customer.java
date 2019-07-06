package ro.sci.gr14.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue("2")
public class Customer extends BaseUser {

    private String address;

    public Customer(Long id, String userName, String password, String email, String fullName, String phoneNumber, String city, String county, String address, Integer role) {
        super(id, userName, password, email, fullName, phoneNumber, city, county, 2);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + super.toString()+
                " address='" + address + '\'' +
                '}';
    }
}
