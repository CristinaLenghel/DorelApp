package ro.sci.gr14.model;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

/**
 * Creates instances of Schedule class for a {@link Handyman} instance
 * Provides comparing methods due to the extension of @link Comparable} interface
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
@NoArgsConstructor

public class Schedule implements Comparable<Schedule> {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    private Long id = 0L;
    @Column(name = "hourin", length = 10)
    private String hourin;
    @Column(name = "hourout", length = 10)
    private String hourout;
    @Column(columnDefinition = "varchar(10)")
    private WeekDays day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "handyman_id", referencedColumnName = "id", nullable = false)
    private Handyman handyman;

    /**
     * Creates new instances of Schedule using three parameters
     *
     * @param hourin  a String containing the hour at which the {@link Handyman} starts working
     * @param hourout a String containing the hour at which the {@link Handyman} stops working
     * @param day     an instance of the {@link WeekDays} class
     */
    public Schedule(String hourin, String hourout, WeekDays day){
        this.hourin = hourin;
        this.hourout = hourout;
        this.day = day;
    }

    /**
     * Method overriden from the {@link Object} class
     *
     * @return a String containing a specific representation of Handyman class instances
     */
    @Override
    public String toString( ){
        return "Schedule{" +
                "Id=" + id +
                ", hourin='" + hourin + '\'' +
                ", hourout='" + hourout + '\'' +
                ", day=" + day +
                '}';
    }

    /**
     * Method overriden from the {@link Object} class
     * Compares memory location and only return true if two reference variable
     * are pointing to same memory location i.e. essentially they are same object
     *
     * @return true if the two objects are equal
     */
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(getId(), schedule.getId()) &&
                getHourin().equals(schedule.getHourin()) &&
                getHourout().equals(schedule.getHourout()) &&
                getDay() == schedule.getDay() &&
                getHandyman().equals(schedule.getHandyman());
    }

    /**
     * Method overriden from the {@link Object} class
     *
     * @return result as an int
     */
    @Override
    public int hashCode( ){
        int result = 17;

        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + hourin.hashCode();
        result = 31 * result + hourout.hashCode();
        result = 31 * result + day.toString().hashCode();

        return result;
    }

    /**
     * Compares two strings lexicographically
     *
     * @param otherSchedule verified instance
     * @return an int value
     */
    @Override
    public int compareTo(Schedule otherSchedule){
        return this.day.getValue() - otherSchedule.getDay().getValue();
    }
}