
/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

package ro.sci.gr14.security;

/**
 * This interface describes the functions of a generic security service
 * It extends the base Service interface and defines methods to query the current security status
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
public interface SecurityService {
    String findLoggedInUsername( );

    /**
     * Method that allows login to be performed automatically
     *
     * @param username a String containing the username of the user
     * @param password a String containing the password of the user
     */
    void autoLogin(String username, String password);
}
