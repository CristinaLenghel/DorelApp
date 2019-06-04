package ro.sci.gr14.model;

import java.util.Date;

public class Handyman extends BaseUser {

    private String specialty;
    private Date workingSince;
    private int pricePerHour;
    private Rating rating;
    private Schedule schedule;

    public Handyman(Long id, String userName, String password, String email, String fullName, String phoneNumber, String city, String county, String specialty, Date workingSince, int pricePerHour, Rating rating, Schedule schedule, String role) {
        super(id, userName, password, email, fullName, phoneNumber, city, county,role);
        this.specialty = specialty;
        this.workingSince = workingSince;
        this.pricePerHour = pricePerHour;
        this.rating = rating;
        this.schedule = schedule;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Date getWorkingSince() {
        return workingSince;
    }

    public void setWorkingSince(Date workingSince) {
        this.workingSince = workingSince;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}