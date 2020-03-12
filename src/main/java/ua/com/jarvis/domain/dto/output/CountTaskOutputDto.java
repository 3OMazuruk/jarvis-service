package ua.com.jarvis.domain.dto.output;

import lombok.Data;
import ua.com.jarvis.domain.TypeTask;

import java.util.Map;

@Data
public class CountTaskOutputDto {

    private Map<TypeTask, Integer> countTask;

    public CountTaskOutputDto() {
    }

    public CountTaskOutputDto(Map<TypeTask, Integer> countTask) {
        this.countTask = countTask;
    }

}
