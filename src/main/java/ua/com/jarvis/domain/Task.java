package ua.com.jarvis.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_task")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "is_done")
    private Boolean isDone;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private TypeTask type;
}
