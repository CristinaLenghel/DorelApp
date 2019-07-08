package ro.sci.gr14.model;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * POJO class used to create county instances for users
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
public class County implements Comparable<County> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    private Long id;
    private String name;

    /*
     * Default constructor for County instances
     */
    public County( ){
        this.id = 1L;
        this.name = "";
    }

    /**
     * Creates new instances of County using two parameters
     *
     * @param id   an int containing the id of the instance
     * @param name a String containing the name of the county
     */
    public County(Long id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * Compares two strings lexicographically
     *
     * @param o verified instance
     * @return an int value
     */
    public int compareTo(County o){
        return this.name.compareTo(o.name);
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
        if (!(o instanceof County)) return false;
        County county = (County) o;
        return getId().equals(county.getId()) &&
                getName().equals(county.getName());
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
        result = result * 31 + (name == null ? 0 : name.hashCode());
        return result;
    }
}
