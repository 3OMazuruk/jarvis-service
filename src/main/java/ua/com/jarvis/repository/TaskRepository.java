package ua.com.jarvis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.jarvis.domain.Task;
import ua.com.jarvis.domain.TypeTask;
import ua.com.jarvis.domain.User;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    Task findByIdAndUser(Long id, User user);
    List<Task> findByDescriptionAndUser(String description, User user);
    List<Task> findByIsDoneAndUser(Boolean isDone, User user);
    List<Task> findByTypeAndUser(TypeTask type, User user);
    List<Task> findByTypeAndIsDoneAndUser(TypeTask type, Boolean isDone, User user);
    List<Task> findByDescriptionAndIsDoneAndTypeAndUser(String description, TypeTask type, Boolean isDone, User user);
    int countTaskByTypeAndUser(TypeTask type, User user);
}
