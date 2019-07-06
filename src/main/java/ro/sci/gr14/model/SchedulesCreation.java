package ro.sci.gr14.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@Data
@Getter
@Setter
public class SchedulesCreation {
    private List<Schedule> schedules;

    public SchedulesCreation(){
        schedules=new ArrayList<Schedule>();
    }

    public SchedulesCreation(ArrayList<Schedule> schedules){
        this.schedules=schedules;
    }

    public SchedulesCreation(Set<Schedule> schedules){
        this.schedules=new ArrayList<Schedule>();
        this.schedules.addAll(schedules);
        Collections.sort(this.schedules);
    }

    public void setHandyman(Handyman handyman){
        schedules.forEach(s->setHandyman(handyman));
        log.info("Finish set handy man");
    }
}
