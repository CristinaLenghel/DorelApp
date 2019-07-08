package ro.sci.gr14.model;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;

/**
 * POJO class used to create generic instances of users
 * Serves as a parent class for the more specific {@link Handyman}, {@link Customer} and {@link Admin} classes
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Slf4j
@Entity(name = "base_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("3")
public class BaseUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    private Long id;
    @Column(name = "username", unique = true)
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password in required")
    private String password;
    @NotBlank(message = "Email is required")
    private String email;
    @Column(name = "fullname")
    @NotBlank(message = "Full name is required")
    private String fullname;
    @Column(name = "phonenumber")
    @NotBlank(message = "Phone number is required")
    private String phonenumber;
    @NotBlank(message = "*")
    private String city;
    @NotBlank(message = "*")
    private String county;
    @Column(insertable = false, updatable = false)
    private Integer role = 0;

    /*
     * Default constructor for BaseUser instances
     */
    public BaseUser( ){
        id = 0L;
        username = "";
        password = "";
        email = "";
        fullname = "";
        phonenumber = "";
        city = "";
        county = "";
        role = 0;
    }

    /**
     * Creates new instances of BaseUser using more parameters
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
    public BaseUser(Long id, String username, String password, String email, String fullname, String phonenumber, String city, String county, Integer role){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.city = city;
        this.county = county;
        this.role = role;
    }

    /**
     * @return the id as an int
     */
    public Long getId( ){
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * @return the username as a String
     */
    public String getUsername( ){
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * @return the password as a String
     */
    public String getPassword( ){
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * @return the email as a String
     */
    public String getEmail( ){
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * @return the fullname as a String
     */
    public String getFullname( ){
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    /**
     * @return the phonenumber as a String
     */
    public String getPhonenumber( ){
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }

    /**
     * @return the city as a String
     */
    public String getCity( ){
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city){
        this.city = city;
    }

    /**
     * @return the county as a String
     */
    public String getCounty( ){
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county){
        this.county = county;
    }

    /**
     * @return the role as an int
     */
    public Integer getRole( ){
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Integer role){
        this.role = role;
    }

    /**
     * Method overriden from the {@link Object} class
     *
     * @return a String containing a specific representation of BaseUser class instances
     */
    @Override
    public String toString( ){
        return "BaseUser{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", role=" + role +
                '}';
    }

    /**
     * Method overriden from the {@link Object} class
     * Compares memory location and only return true if two reference variable
     * are pointing to same memory location i.e. essentially they are same object
     *
     * @param o verified object
     * @return true if the two objects are equal
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof BaseUser)) return false;
        BaseUser baseUser = (BaseUser) o;
        return getId().equals(baseUser.getId()) &&
                getUsername().equals(baseUser.getUsername()) &&
                getPassword().equals(baseUser.getPassword()) &&
                getEmail().equals(baseUser.getEmail()) &&
                getFullname().equals(baseUser.getFullname()) &&
                getPhonenumber().equals(baseUser.getPhonenumber()) &&
                getCity().equals(baseUser.getCity()) &&
                getCounty().equals(baseUser.getCounty()) &&
                getRole().equals(baseUser.getRole());
    }

    /**
     * Method overriden from the {@link Object} class
     *
     * @return result as an int
     */
    @Override
    public int hashCode( ){
        int result = 17;

        result = result * 31 + (int) (id ^ (id >>> 32));
        result = result * 31 + (username == null ? 0 : username.hashCode());
        result = result * 31 + (password == null ? 0 : password.hashCode());
        result = result * 31 + (email == null ? 0 : email.hashCode());
        result = result * 31 + (fullname == null ? 0 : fullname.hashCode());
        result = result * 31 + (phonenumber == null ? 0 : phonenumber.hashCode());
        result = result * 31 + (city == null ? 0 : city.hashCode());
        result = result * 31 + (county == null ? 0 : county.hashCode());
        result = result * 31 + role;
        return result;
    }

    /**
     * Overriden method of {@link GrantedAuthority} interface which extends Serializable
     *
     * @return role
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities( ){
        log.info("User :" + this);
        log.info("Baza User Role value: " + role);
        log.info("Get role: " + Role.values()[role]);
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + Role.values()[role]));
    }

    /**
     * Overiden method of UserDetailsService interface
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired( ){
        return true;
    }

    /**
     * Overiden method of UserDetailsService interface
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked( ){
        return true;
    }

    /**
     * Overiden method of UserDetailsService interface
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired( ){
        return true;
    }

    /**
     * Overiden method of UserDetailsService interface
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled( ){
        return true;
    }
}
