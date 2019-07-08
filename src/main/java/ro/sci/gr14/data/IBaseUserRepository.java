package ro.sci.gr14.data;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.sci.gr14.model.BaseUser;

/**
 * Repository interface to manage {@link BaseUser} instances
 * Provides basic CRUD operations due to the extension of {@link CrudRepository}
 * Marked as a Data Access Object
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */

@Repository
public interface IBaseUserRepository extends CrudRepository<BaseUser, Long> {

    /**
     * Returns the BaseUser with the given username
     *
     * @param username a String representing the account name of the user
     */
    BaseUser findByUsername(String username);
}