package ro.sci.gr14.model;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@Entity(name="base_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
//@Table(name="base_user")
public class BaseUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    //@SequenceGenerator(name = "auto_gen", sequenceName = "A")
    private Long id;
    @Column(name="username", unique = true)
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
    private Integer role=0;

    public BaseUser(){
        id=0L;
        username="";
        password="";
        email="";
        fullname="";
        phonenumber="";
        city="";
        county="";
        role=0;
    }

    public BaseUser(Long id, String userName, String password, String email, String fullname, String phonenumber, String city, String county, Integer role) {
        this.id = id;
        this.username = userName;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.city = city;
        this.county = county;
        this.role=role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
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

    @Override
    public boolean equals(Object o) {
        if (o==null) return false;
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

    @Override
    public int hashCode() {
       int result=17;

       result=result*31+(int) (id ^ (id >>> 32));
       result=result*31+(username == null ? 0 : username.hashCode());
       result=result*31+(password == null ? 0 : password.hashCode());
       result=result*31+(email == null ? 0 : email.hashCode());
       result=result*31+(fullname == null ? 0 : fullname.hashCode());
       result=result*31+(phonenumber == null ? 0 : phonenumber.hashCode());
       result=result*31+(city == null ? 0 : city.hashCode());
       result=result*31+(county == null ? 0 : county.hashCode());
       result=result*31+role;
       return result;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("User :" +this);
        log.info("Baza User Role value: " + role);
        log.info("Get role: "+ Role.values()[role]);
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+ Role.values()[role]));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}