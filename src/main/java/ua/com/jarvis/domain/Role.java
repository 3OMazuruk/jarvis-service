package ua.com.jarvis.domain;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * INSERT INTO role (NAME) VALUES
 * 		('ROLE_ADMIN'),('ROLE_TEACHER'),('ROLE_STUDENT');
 */

@Data
@Entity
@Table(name = "role")
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name_role")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_role", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private List<Permission> permissions;

}
