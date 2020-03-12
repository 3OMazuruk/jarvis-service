package ua.com.jarvis.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.com.jarvis.domain.User;
import ua.com.jarvis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void create(User user) {
        User existing = findByUsername(user.getUsername());
        if(Objects.nonNull(existing)){
            user.setPassword("password");
            log.warn(("User already exists! " + user));
            throw new IllegalArgumentException("User already exists: " + user);
        }

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        userRepository.save(user);

        log.info("New user has been created: " + user.getUsername());

        return;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){ return user.get(); }

        user = userRepository.findByEmail(username);

        if(user.isPresent()){ return user.get(); }

        user = userRepository.findByGoogleUsername(username);

        if(user.isPresent()){ return user.get(); }

        user = userRepository.findByGoogleName(username);

        if(user.isPresent()){ return user.get(); }

        return null;
    }
}
