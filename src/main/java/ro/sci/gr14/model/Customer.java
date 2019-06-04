package ro.sci.gr14.model;

public class Customer extends BaseUser {

    private String address;

    public Customer(Long id, String userName, String password, String email, String fullName, String phoneNumber, String city, String county, String address, String role) {
        super(id, userName, password, email, fullName, phoneNumber, city, county, role);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
