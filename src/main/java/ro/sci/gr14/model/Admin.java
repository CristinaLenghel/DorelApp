package ro.sci.gr14.model;

public class Admin extends BaseUser {

    public Admin(int id, String userName, String password, String email, String fullName, String phoneNumber, String city, String county) {
        super(id, userName, password, email, fullName, phoneNumber, city, county);
    }
}
