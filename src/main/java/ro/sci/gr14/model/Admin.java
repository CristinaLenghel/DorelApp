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
@DiscriminatorValue("0")
public class Admin extends BaseUser {

    /**
     * Creates new instances of class Admin taking more parameters
     *
     * @param id          an int containing the id of this particular user
     * @param username    a String containing the username of this particular user
     * @param password    a String containing the password of this particular user
     * @param email       a String containing the email of this particular user
     * @param fullname    a String containing the fullname of this particular user
     * @param phonenumber a String containing the phonenumber of this particular user
     * @param city        a String containing the city of this particular user
     * @param county      a String containing the county of this particular user
     * @param role        a String containing the role of this particular user
     */
    public Admin(Long id, String username, String password, String email, String fullname, String phonenumber, String city, String county, Integer role){
        super(id, username, password, email, fullname, phonenumber, city, county, role);
    }
}
