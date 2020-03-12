package ua.com.jarvis.domain.dto.output;

import ua.com.jarvis.domain.Task;
import java.util.List;

public class ListTaskOutputDto {

    private List<Task> tasks;

    public ListTaskOutputDto() {
    }

    public ListTaskOutputDto(List<Task> tasks) {
        this.tasks = tasks;
    }
}
