package ua.com.jarvis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.jarvis.domain.Task;
import ua.com.jarvis.domain.TypeTask;
import ua.com.jarvis.domain.User;
import ua.com.jarvis.repository.TaskRepository;

import java.util.List;

@Slf4j
@Service
public class TaskService implements DataService<Task> {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll(User user){
        log.info("");

        return taskRepository.findByUser(user);
    }

    @Override
    public Task save(Task entity) {
        log.info("");

        return taskRepository.save(entity);
    }

    @Override
    public Task findById(Long id, User user) {
        log.info("");

        return taskRepository.findByIdAndUser(id, user);
    }

    public List<Task> findByIsDoneAndUser(Boolean isDone, User user){
        log.info("");

        return taskRepository.findByIsDoneAndUser(isDone, user);
    }

    public List<Task> findByTypeTaskAndUser(TypeTask typeTask, User user){
        log.info("");

        return taskRepository.findByTypeAndUser(typeTask, user);
    }

    public List<Task> findByTypeTaskAndIsDoneAndUser(TypeTask typeTask, Boolean isDone, User user){
        log.info("");

        return taskRepository.findByTypeAndIsDoneAndUser(typeTask, isDone, user);
    }

    public int countTaskByType(TypeTask type, User user){
        log.info("");

        return taskRepository.countTaskByTypeAndUser(type, user);
    }

    @Override
    public void delete(Task entity) {
        log.info("");

        taskRepository.delete(entity);

        return;
    }

}
