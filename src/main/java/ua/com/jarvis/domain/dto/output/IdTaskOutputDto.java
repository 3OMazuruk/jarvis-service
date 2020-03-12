package ua.com.jarvis.domain.dto.output;

import lombok.Data;
import ua.com.jarvis.domain.Task;

@Data
public class IdTaskOutputDto {

    private Task task;

    public IdTaskOutputDto() {
    }

    public IdTaskOutputDto(Task task) {
        this.task = task;
    }
}
