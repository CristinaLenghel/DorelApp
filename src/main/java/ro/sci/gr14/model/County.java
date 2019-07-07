package ro.sci.gr14.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Entity
@Data
public class County implements Comparable<County>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    private Long id;
    private String name;
    //private Set<City> cities=new TreeSet<>();

    public County(){
        this.id=1L;
        this.name="";
    }

    public County(Long id, String name){
        this.id=id;
        this.name=name;
    }

//    public County addCity(City city) {
//        this.cities.add(city);
//        city.setCounty(this.name);
//        return this;
//    }


    public int compareTo(County o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o==null) return false;
        if (this == o) return true;
        if (!(o instanceof County)) return false;
        County county = (County) o;
        return  getId().equals(county.getId()) &&
                getName().equals(county.getName());
    }

    @Override
    public int hashCode() {
        int result=17;
        result=result*31+(int) (id ^ (id >>> 32));
        result=result*31+(name == null ? 0 : name.hashCode());
        return  result;
    }
}
