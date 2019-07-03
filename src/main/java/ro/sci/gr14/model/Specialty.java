package ro.sci.gr14.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "specialty")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    private Long id=0L;
    private String specialtyname="";
    private Integer workingsince=0;
    private int priceperhour=0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "handyman_id",referencedColumnName="id",nullable=false)
    private Handyman handyman;

    public Specialty(){
        id=0L;
        specialtyname="";
        workingsince=0;
        priceperhour=0;
    }
    public Specialty(String specialtyname, Integer workingSince, int pricePerHour) {
        this.specialtyname = specialtyname;
        this.workingsince = workingSince;
        this.priceperhour = pricePerHour;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "Id=" + id +
                ", specialtyname='" + specialtyname + '\'' +
                ", workingSince=" + workingsince +
                ", pricePerHour=" + priceperhour +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o==null) return false;
        if (this == o) return true;
        if (!(o instanceof Specialty)) return false;
        Specialty specialty = (Specialty) o;
        return getPriceperhour() == specialty.getPriceperhour() &&
                getId().equals(specialty.getId()) &&
                getSpecialtyname().equals(specialty.getSpecialtyname()) &&
                getWorkingsince().equals(specialty.getWorkingsince()) &&
                getHandyman().equals(specialty.getHandyman());
    }

    @Override
    public int hashCode() {
       int result=17;

       result=result*31+(int) (id ^ (id >>> 32));
       result=result*31+(specialtyname == null ? 0 : specialtyname.hashCode());
       result=result*31+priceperhour;
       result=result*31+workingsince;
       return  result;
    }
}
