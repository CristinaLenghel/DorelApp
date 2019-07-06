package ro.sci.gr14.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.model.Customer;

import java.util.List;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByFullname(String fullname);
    Customer findByUsername(String username);
}