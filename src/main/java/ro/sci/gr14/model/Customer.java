package ro.sci.gr14.model;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * POJO child class of the {@link BaseUser} class
 * Is used to create more specific instances of the BaseUser class
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
@Entity
@NoArgsConstructor
@DiscriminatorValue("2")
public class Customer extends BaseUser {

    private String address;

    /**
     * Creates new instances of Customer using more parameters
     *
     * @param id          an int containing the id of this particular user
     * @param username    a String containing the username of this particular user
     * @param password    a String containing the password of this particular user
     * @param email       a String containing the email of this particular user
     * @param fullname    a String containing the fullname of this particular user
     * @param phonenumber a String containing the phonenumber of this particular user
     * @param city        a String containing the city of this particular user
     * @param county      a String containing the county of this particular user
     * @param address     a String containing the address of this particular user
     * @param role        a String containing the role of this particular user
     */
    public Customer(Long id, String username, String password, String email, String fullname, String phonenumber, String city, String county, String address, Integer role){
        super(id, username, password, email, fullname, phonenumber, city, county, 2);
        this.address = address;
    }

    /**
     * @return the address as a String
     */
    public String getAddress( ){
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Method overriden from the {@link BaseUser} class
     *
     * @return a String containing a specific representation of Customer class instances
     */
    @Override
    public String toString( ){
        return "Customer{" + super.toString() +
                " address='" + address + '\'' +
                '}';
    }
}
