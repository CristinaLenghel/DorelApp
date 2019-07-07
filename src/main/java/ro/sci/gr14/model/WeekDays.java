package ro.sci.gr14.model;

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
 * Enum class for the seven days of the week
 */
public enum WeekDays {

    LUNI(0), MARTI(1), MIERCURI(2), JOI(3),
    VINERI(4), SAMBATA(5), DUMINICA(6);

    public final int val;

    /*
     * Creates new instances of WeekDays using one parameter
     *
     * @param val an int representing the day of the week's number
     */
    private WeekDays(int val){
        this.val = val;
    }

    /**
     * @return val as an int
     */
    public int getValue( ){
        return val;
    }
}

