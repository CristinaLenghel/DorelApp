package ro.sci.gr14.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.sci.gr14.model.BaseUser;

import java.util.List;

@Repository
public interface IBaseUserRepository extends CrudRepository<BaseUser, Long> {
    List<ro.sci.gr14.model.BaseUser> findByFullName(String fullName);
}