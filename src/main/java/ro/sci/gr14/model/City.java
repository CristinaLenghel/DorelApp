package ro.sci.gr14.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Slf4j
@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    private Long id;
    private String name;

    public City(){
        this.id=1L;
        this.name="";
    }

    public City(Long id, String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {
        if (o==null) return false;
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City county = (City) o;
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
