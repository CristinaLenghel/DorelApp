package ro.sci.gr14.model;

import java.util.Date;
import java.util.LinkedList;

public class Schedule {

    private Date scheduleHoursIn;
    private Date scheduleHoursOut;
    private LinkedList<WeekDays> scheduleDays;

    public Schedule(Date scheduleHoursIn, Date scheduleHoursOut, LinkedList<WeekDays> scheduleDays) {
        this.scheduleHoursIn = scheduleHoursIn;
        this.scheduleHoursOut = scheduleHoursOut;
        this.scheduleDays = scheduleDays;
    }

    public Date getScheduleHoursIn() {
        return scheduleHoursIn;
    }

    public void setScheduleHoursIn(Date scheduleHoursIn) {
        this.scheduleHoursIn = scheduleHoursIn;
    }

    public Date getScheduleHoursOut() {
        return scheduleHoursOut;
    }

    public void setScheduleHoursOut(Date scheduleHoursOut) {
        this.scheduleHoursOut = scheduleHoursOut;
    }

    public LinkedList<WeekDays> getScheduleDays() {
        return scheduleDays;
    }

    public void setScheduleDays(LinkedList<WeekDays> scheduleDays) {
        this.scheduleDays = scheduleDays;
    }
}
