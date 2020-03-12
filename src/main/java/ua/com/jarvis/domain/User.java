package ua.com.jarvis.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * insert into user (id, username,password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked)
 * VALUES
 * (null, 'user','{bcrypt}$2a$10$4iCF3108kbFeaN1nYbeBcu5WA7LLc.VL/c41yNRfvvEwk7P4OHfWO', 'k@krishantha.com', '1', '1', '1', '1');
 */

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "google_name")
    private String googleName;

    @Column(name = "google_username")
    private String googleUsername;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    public User() {
    }

    public User(String username, String password, boolean accountNonExpired, boolean accountNonLocked,
                    boolean credentialsNonExpired, boolean enabled, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.roles = roles;
    }
}
