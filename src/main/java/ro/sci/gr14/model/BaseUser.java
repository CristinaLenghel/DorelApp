package ro.sci.gr14.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;


@Entity
@Table(name="base_user")
public class BaseUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    @SequenceGenerator(name = "auto_gen", sequenceName = "A")
    private Long id;
    @Column(name="username")
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
    private String role="user";

    public BaseUser(){
    }

    public BaseUser(Long id, String userName, String password, String email, String fullname, String phonenumber, String city, String county, String role) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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