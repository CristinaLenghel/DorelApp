package ro.sci.gr14.model;

public enum WeekDays {

    LUNI(0), MARTI(1), MIERCURI(2), JOI(3),
    VINERI(4), SAMBATA(5), DUMINICA(6);

    public final int val;

    private WeekDays(int val){
        this.val=val;
    }

    public int getValue(){
        return val;
    }
}
