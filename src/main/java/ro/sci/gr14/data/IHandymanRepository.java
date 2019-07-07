package ro.sci.gr14.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.sci.gr14.model.BaseUser;
import ro.sci.gr14.model.Handyman;

import java.util.List;

@Repository
public interface IHandymanRepository extends CrudRepository<Handyman, Long> {
    List<Handyman> findByFullname(String fullname);
    Handyman findByUsername(String username);
    List<Handyman> findByRole(Integer role);
    List<Handyman> findByCounty(String county);
}