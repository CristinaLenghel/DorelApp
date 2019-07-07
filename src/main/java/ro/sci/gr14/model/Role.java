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
 * Enum class for the three types of users {@link Customer}, {@link Handyman}, {@link Admin}
 */
public enum Role {
    ADMIN(0), HANDYMAN(1), CUSTOMER(2);

    public final int val;

    /**
     * Creates new instances of Role using one parameter
     *
     * @param val an int representing the role number
     */
    private Role(int val){
        this.val = val;
    }

    /**
     * @return val as an int
     */
    public int getRoleValue( ){
        return val;
    }
}

