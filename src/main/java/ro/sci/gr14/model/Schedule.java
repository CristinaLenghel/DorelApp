package ro.sci.gr14.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Objects;

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

    public Schedule(String hourin, String hourout, WeekDays day) {
        this.hourin = hourin;
        this.hourout = hourout;
        this.day = day;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "Id=" + id +
                ", hourin='" + hourin + '\'' +
                ", hourout='" + hourout + '\'' +
                ", day=" + day +
                '}';
    }

    @Override
    public boolean equals(Object o) {
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

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + hourin.hashCode();
        result = 31 * result + hourout.hashCode();
        result = 31 * result + day.toString().hashCode();

        return result;
    }

    @Override
    public int compareTo(Schedule otherSchedule) {
        log.info(""+(this.day.getValue()- otherSchedule.getDay().getValue()));
        return this.day.getValue()- otherSchedule.getDay().getValue();
    }
}