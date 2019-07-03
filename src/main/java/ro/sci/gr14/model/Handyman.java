package ro.sci.gr14.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

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

    //private Rating rating;
    //private Schedule_1 schedule;

    public Handyman(){
        specialties=new HashSet<>();
        schedules=new TreeSet<>();
    }
    public Handyman(Long id, String userName, String password, String email, String fullName, String phoneNumber,
                    String city, String county, Integer role, Set<Specialty> specialties, TreeSet<Schedule> schedules) {
        super(id, userName, password, email, fullName, phoneNumber, city, county, 1);
        this.specialties = specialties;
        //this.rating = rating;
        this.schedules = schedules;
    }

    public void addSpecialty(Specialty specialty){
        specialty.setHandyman(this);
        specialties.add(specialty);
    }
    public void removeSpecialty(Specialty specialty){
        specialties.remove(specialty);
    }

    public void addSchedule(Schedule daySchedule){
        daySchedule.setHandyman(this);
        schedules.add(daySchedule);
    }

//    public void removeSchedule(Schedule daySchedule){
//        specialties.remove(daySchedule);
//    }

    @Override
    public String toString() {
        return "Handyman{" + super.toString()+
                " specialties= " + specialties +
                " schedules= "+ schedules+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Handyman)) return false;
        Handyman handyman = (Handyman) o;
        return Objects.equals(getSpecialties(), handyman.getSpecialties()) &&
                Objects.equals(getSchedules(), handyman.getSchedules());
    }

    @Override
    public int hashCode() {
        int result=17;

        result=result*31+ specialties.hashCode();
        result=result*31+ schedules.hashCode();
        result=result*31+ super.hashCode();

        return result;
    }
}