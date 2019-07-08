package ro.sci.gr14.model;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import javax.persistence.*;
import java.util.*;

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
@Slf4j
@Entity
@Data
@DiscriminatorValue("1")
public class Handyman extends BaseUser {
    @OneToMany(mappedBy = "handyman", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Specialty> specialties;

    @OneToMany(mappedBy = "handyman", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Schedule> schedules;

    public Handyman( ){
        specialties = new HashSet<>();
        schedules = new TreeSet<>();
    }

    /**
     * Creates new instances of Handyman using more parameters
     *
     * @param id          an int containing the id of this particular user
     * @param username    a String containing the username of this particular user
     * @param password    a String containing the password of this particular user
     * @param email       a String containing the email of this particular user
     * @param fullname    a String containing the fullname of this particular user
     * @param phonenumber a String containing the phonenumber of this particular user
     * @param city        a String containing the city of this particular user
     * @param county      a String containing the county of this particular user
     * @param specialties an instance of {@Specialty} class
     * @param schedules   an instance of {@Schedule} class
     * @param role        a String containing the role of this particular user
     */
    public Handyman(Long id, String username, String password, String email, String fullname, String phonenumber,
                    String city, String county, Integer role, Set<Specialty> specialties, TreeSet<Schedule> schedules){
        super(id, username, password, email, fullname, phonenumber, city, county, 1);
        this.specialties = specialties;
        this.schedules = schedules;
    }

    /**
     * Adds a {@link Specialty} object to a Handyman instance
     *
     * @param specialty the instance to be added
     */
    public void addSpecialty(Specialty specialty){
        specialty.setHandyman(this);
        specialties.add(specialty);
    }

    /**
     * Removes a {@link Specialty} object from a Handyman instance
     *
     * @param specialty the instance to be added
     */
    public void removeSpecialty(Specialty specialty){
        specialties.remove(specialty);
    }

    /**
     * Adds a {@link Schedule} object to a Handyman instance
     *
     * @param daySchedule the instance to be added
     */
    public void addSchedule(Schedule daySchedule){
        daySchedule.setHandyman(this);
        schedules.add(daySchedule);
    }

    /**
     * Creates empty Sets for {@link Schedule} objects
     *
     * @return schedules
     */
    public Set<Schedule> getSchedules( ){
        if (schedules.size() == 0)
            createEmptySchedulesSet();
        return schedules;
    }

    /*
     * Creates empty Sets for {@link Schedule} objects for every {@link WeekDays} instance
     */
    public void createEmptySchedulesSet( ){
        log.info("createEmptySchedule Start");
        for (WeekDays day : WeekDays.values()) {
            this.addSchedule(new Schedule("", "", day));
        }
    }

    /**
     * Method overriden from the {@link BaseUser} class
     *
     * @return a String containing a specific representation of Handyman class instances
     */
    @Override
    public String toString( ){
        return "Handyman{" + super.toString() +
                " specialties= " + specialties +
                " schedules= " + schedules +
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
        if (this == o) return true;
        if (!(o instanceof Handyman)) return false;
        Handyman handyman = (Handyman) o;
        return Objects.equals(getSpecialties(), handyman.getSpecialties()) &&
                Objects.equals(getSchedules(), handyman.getSchedules());
    }

    /**
     * Verifies object inequality for this class
     *
     * @return result as an int
     */
    @Override
    public int hashCode( ){
        int result = 17;

        result = result * 31 + specialties.hashCode();
        result = result * 31 + schedules.hashCode();
        result = result * 31 + super.hashCode();

        return result;
    }
}