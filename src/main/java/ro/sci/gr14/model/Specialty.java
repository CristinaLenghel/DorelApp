package ro.sci.gr14.model;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;

import javax.persistence.*;

/**
 * Creates instances of Specialty class for a {@link Handyman} instance
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
@Table(name = "specialty")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    private Long id = 0L;
    private String specialtyname = "";
    private Integer workingsince = 0;
    private int priceperhour = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "handyman_id", referencedColumnName = "id", nullable = false)
    private Handyman handyman;

    /**
     * Default constructor for Specialty instances
     */
    public Specialty( ){
        id = 0L;
        specialtyname = "";
        workingsince = 0;
        priceperhour = 0;
    }

    /**
     * Creates new instances of Specialty using nine parameters
     *
     * @param specialtyname a String containing the specialty name of the handyman
     * @param workingSince  an Integer containing the year when the handyman started his experience
     * @param pricePerHour  an int containing the price for an hour of work
     */
    public Specialty(String specialtyname, Integer workingSince, int pricePerHour){
        this.specialtyname = specialtyname;
        this.workingsince = workingSince;
        this.priceperhour = pricePerHour;
    }

    /**
     * Method overriden from the {@link Object} class
     *
     * @return a String containing a specific representation of Handyman class instances
     */
    @Override
    public String toString( ){
        return "Specialty{" +
                "Id=" + id +
                ", specialtyname='" + specialtyname + '\'' +
                ", workingSince=" + workingsince +
                ", pricePerHour=" + priceperhour +
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
        if (!(o instanceof Specialty)) return false;
        Specialty specialty = (Specialty) o;
        return getPriceperhour() == specialty.getPriceperhour() &&
                getId().equals(specialty.getId()) &&
                getSpecialtyname().equals(specialty.getSpecialtyname()) &&
                getWorkingsince().equals(specialty.getWorkingsince()) &&
                getHandyman().equals(specialty.getHandyman());
    }

    /**
     * Verifies object inequality for this class
     *
     * @return result as an int
     */
    @Override
    public int hashCode( ){
        int result = 17;

        result = result * 31 + (int) (id ^ (id >>> 32));
        result = result * 31 + (specialtyname == null ? 0 : specialtyname.hashCode());
        result = result * 31 + priceperhour;
        result = result * 31 + workingsince;
        return result;
    }
}