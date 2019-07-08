package ro.sci.gr14.security;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.sci.gr14.data.IBaseUserRepository;
import ro.sci.gr14.model.BaseUser;

/**
 * This class is used as service provider
 * Is able to load user-specific data due to implementing the {@link UserDetailsService} interface
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Service
public class UserRepositoryUserDetailsService
        implements UserDetailsService {

    private IBaseUserRepository userRepo;

    /**
     *
     * @param userRepo
     */
    @Autowired
    public UserRepositoryUserDetailsService(IBaseUserRepository userRepo){
        this.userRepo = userRepo;
    }

    /**
     * Locates the user based on the username
     *
     * @param username a String containing the username of the user
     * @return user represented by the given username
     * @throws UsernameNotFoundException in the event of a UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{
        BaseUser user = userRepo.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), user.getAuthorities());
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }

}