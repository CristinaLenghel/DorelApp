package ro.sci.gr14.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.sci.gr14.model.Specialty;

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
 * Repository interface to manage {@link Specialty} instances. Provides basic CRUD operations due to the extension of
 * {@link CrudRepository}
 */

@Repository
public interface ISpecialtyRepository extends CrudRepository<Specialty, Long> {
}
