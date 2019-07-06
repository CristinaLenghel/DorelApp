package ro.sci.gr14.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ro.sci.gr14.data.IHandymanRepository;
import ro.sci.gr14.model.Handyman;

public class HandymanRepositoryDetailsService implements UserDetailsService {

    private IHandymanRepository handymanRepo;

    @Autowired
    public HandymanRepositoryDetailsService(IHandymanRepository handymanRepo) {
        this.handymanRepo = handymanRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Handyman handyman = handymanRepo.findByUsername(username);
        if (handyman != null) {
            return handyman;
        }
        throw new UsernameNotFoundException(
                "Handyman '" + username + "' not found");
    }
}
