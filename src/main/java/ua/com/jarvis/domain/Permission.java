package ua.com.jarvis.domain;

import lombok.Data;
import javax.persistence.*;

/**
 * INSERT INTO PERMISSION (NAME) VALUES
 *  ('create_profile'),
 *  ('read_profile'),
 *  ('update_profile'),
 *  ('delete_profile');
 */

@Data
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

}
