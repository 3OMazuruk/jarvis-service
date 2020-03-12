package ua.com.jarvis.service.security;

import ua.com.jarvis.domain.AuthUserDetail;
import ua.com.jarvis.domain.User;
import ua.com.jarvis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> findUsername = repository.findByUsername(username);

        if(findUsername.isPresent())
        {
            return new AuthUserDetail(findUsername.get());
        }

        Optional<User> findEmail = repository.findByEmail(username);

        if(findUsername.isPresent())
        {
            return new AuthUserDetail(findEmail.get());
        }

        Optional<User> findGoogleName = repository.findByGoogleName(username);

        if(findUsername.isPresent())
        {
            return new AuthUserDetail(findEmail.get());
        }

        Optional<User> findGoogleUsername = repository.findByGoogleUsername(username);

        if(findUsername.isPresent())
        {
            return new AuthUserDetail(findEmail.get());
        }

        throw new UsernameNotFoundException(username);
    }

}
