package ua.com.jarvis.domain.dto.output;

import lombok.Data;
import ua.com.jarvis.domain.Task;
import ua.com.jarvis.domain.TypeTask;

@Data
public class TaskOutputDto {

    private Long id_task;
    private Long id_user;
    private String description;
    private Boolean isDone;
    private TypeTask type;

    public TaskOutputDto() {
    }

    public TaskOutputDto(Long id_task, Long id_user, String description, Boolean isDone, TypeTask type) {
        this.id_task = id_task;
        this.id_user = id_user;
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public TaskOutputDto(Task task) {
        this.id_task = task.getId();
        this.description = task.getDescription();
        this.isDone = task.getIsDone();
        this.id_user = task.getUser().getId();
        this.type = task.getType();
    }
}
