package ro.sci.gr14.model;

public class BaseUser {

    private int id;
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String city;
    private String county;

    public BaseUser(){
        this.id = 0;
        this.userName = "";
        this.password = "";
        this.email = "";
        this.fullName = "";
    }

    public BaseUser(int id, String userName, String password, String email, String fullName, String phoneNumber, String city, String county) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.county = county;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}