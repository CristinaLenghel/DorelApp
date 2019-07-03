package ro.sci.gr14.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.sci.gr14.model.Admin;


import java.util.List;

@Repository
public interface IAdminRepository extends CrudRepository<Admin, Long> {
    List<Admin> findByFullname(String fullname);
    Admin findByUsername(String username);
}
