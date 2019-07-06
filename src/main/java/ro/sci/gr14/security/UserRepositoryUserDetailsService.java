package ro.sci.gr14.security;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.
        UserDetailsService;
import org.springframework.security.core.userdetails.
        UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.model.BaseUser;


@Service
public class UserRepositoryUserDetailsService
        implements UserDetailsService {

    private IBaseUserRepository userRepo;

    @Autowired
    public UserRepositoryUserDetailsService(IBaseUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        BaseUser user = userRepo.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), user.getAuthorities());
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }

}