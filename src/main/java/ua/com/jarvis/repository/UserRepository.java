package ua.com.jarvis.repository;

import ua.com.jarvis.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByGoogleName(String googleName);
    Optional<User> findByGoogleUsername(String googleUsername);
}
