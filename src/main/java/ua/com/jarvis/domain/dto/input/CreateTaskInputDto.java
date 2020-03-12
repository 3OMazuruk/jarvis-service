package ua.com.jarvis.domain.dto.input;

import lombok.Data;
import ua.com.jarvis.domain.TypeTask;

@Data
public class CreateTaskInputDto {

    private String description;
    private Boolean isDone;
    private TypeTask type;

    public CreateTaskInputDto() {
    }

    public CreateTaskInputDto(String description, Boolean isDone, TypeTask type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }
}
