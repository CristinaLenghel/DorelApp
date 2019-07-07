package ro.sci.gr14.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.sci.gr14.model.BaseUser;

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
 * Repository interface to manage {@link BaseUser} instances. Provides basic CRUD operations due to the extension of
 * {@link CrudRepository}
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