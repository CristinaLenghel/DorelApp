package ro.sci.gr14.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 * <p>
 * Creates a list of daily {@link Schedule} instances to make a weekly schedule for every {@link Handyman} instance
 */
@Slf4j
@Data
@Getter
@Setter
public class SchedulesCreation {
    private List<Schedule> schedules;

    public SchedulesCreation( ){
        schedules = new ArrayList<Schedule>();
    }

    public SchedulesCreation(ArrayList<Schedule> schedules){
        this.schedules = schedules;
    }

    /**
     * Creates an empty set for {@link Schedule} instances
     *
     * @param schedules an instance of {@link Schedule}
     */
    public SchedulesCreation(Set<Schedule> schedules){
        this.schedules = new ArrayList<Schedule>();
        this.schedules.addAll(schedules);
        Collections.sort(this.schedules);
    }

    /**
     * Sets to every schedule the corresponding {@link Handyman} instance
     *
     * @param handyman an instance of {@link Handyman}
     */
    public void setHandyman(Handyman handyman){
        schedules.forEach(s -> setHandyman(handyman));
        log.info("Finish set handy man");
    }
}
