package ro.sci.gr14.model;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * POJO class used to create specialty instances for users
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
@Slf4j
public class DBEntity {
    private Long id;
    private String name;

    /*
     * Default constructor for DBEntity instances
     */
    public DBEntity(){
        this.id=1L;
        this.name="";
    }

    /**
     * Creates new instances of DBEntity using two parameters
     *
     * @param id an int containing the id of the instance
     * @param name a String containing the name of the specialty
     */
    public DBEntity(Long id, String name){
        this.id=id;
        this.name=name;
    }
}
