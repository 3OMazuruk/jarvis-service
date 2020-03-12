package ua.com.jarvis.domain.dto.input;

import lombok.Data;
import ua.com.jarvis.domain.TypeTask;

@Data
public class RefreshTaskInputDto {

    private Long id;
    private String description;
    private Boolean isDone;
    private TypeTask type;

    public RefreshTaskInputDto() {
    }

    public RefreshTaskInputDto(Long id, String description, Boolean isDone, TypeTask type) {
        this.id = id;
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }
}
