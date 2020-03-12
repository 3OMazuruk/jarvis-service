package ua.com.jarvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.jarvis.domain.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
