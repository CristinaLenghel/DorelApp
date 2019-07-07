package ro.sci.gr14.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.model.Handyman;

import java.util.List;

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
 * Repository interface to manage {@link Handyman} instances. Provides basic CRUD operations due to the extension of
 * {@link CrudRepository}
 */

@Repository
public interface IHandymanRepository extends CrudRepository<Handyman, Long> {

    /**
     * Returns the Handyman with the given username
     *
     * @param username a String representing the account name of the user
     */

    Handyman findByUsername(String username);

    /**
     * Returns the Handymanm with the given role
     * @param role a Integer representing the role number of the user
     *
     */
    List<Handyman> findByRole(Integer role);

    /**
     * Returns the Handyman with the given county
     * @param county a String representing the county of the user
     *
     */
    List<Handyman> findByCounty(String county);
}