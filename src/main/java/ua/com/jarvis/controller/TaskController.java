package ua.com.jarvis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ua.com.jarvis.domain.AuthUserDetail;
import ua.com.jarvis.domain.Task;
import ua.com.jarvis.domain.TypeTask;
import ua.com.jarvis.domain.User;
import ua.com.jarvis.domain.dto.input.CreateTaskInputDto;
import ua.com.jarvis.domain.dto.input.RefreshTaskInputDto;
import ua.com.jarvis.domain.dto.output.CountTaskOutputDto;
import ua.com.jarvis.domain.dto.output.IdTaskOutputDto;
import ua.com.jarvis.domain.dto.output.ListTaskOutputDto;
import ua.com.jarvis.domain.dto.output.TaskOutputDto;
import ua.com.jarvis.repository.UserRepository;
import ua.com.jarvis.service.TaskService;
import ua.com.jarvis.service.UserServiceImpl;
import ua.com.jarvis.service.security.JpaUserDetailsService;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getAllTask(Authentication authentication){
        User user = userService.findByUsername(authentication.getName());

        List<TaskOutputDto> tasks = new ArrayList<>();
        taskService.findAll(user).forEach(t -> tasks.add(new TaskOutputDto(t)));

        log.info("@GetMapping(\"/\")");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tasks);
    }

    @GetMapping("/type/{type}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getTaskByType(@PathVariable String type, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());
        TypeTask typeTask = TypeTask.valueOf(type);

        List<TaskOutputDto> tasks = new ArrayList<>();
        taskService.findByTypeTaskAndUser(typeTask, user).forEach(t -> tasks.add(new TaskOutputDto(t)));

        log.info("@GetMapping(\"/{type}\")");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tasks);
    }

    @GetMapping("/{type}/{done}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getIsDoneAndTypeTask(@PathVariable TypeTask type, @PathVariable Boolean done, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());

        List<TaskOutputDto> tasks = new ArrayList<>();
        taskService.findByTypeTaskAndIsDoneAndUser(type, done, user).forEach(t -> tasks.add(new TaskOutputDto(t)));

        log.info("@GetMapping(\"/{type}/{done}\")");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tasks);
    }

    @GetMapping("/done/{done}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getIsDone(@PathVariable Boolean done, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());

        List<TaskOutputDto> tasks = new ArrayList<>();
        taskService.findByIsDoneAndUser(done, user).forEach(t -> tasks.add(new TaskOutputDto(t)));

        log.info("@GetMapping(\"/{done}\")");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tasks);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getTaskById(@PathVariable Long id, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());

        Task task = taskService.findById(id, user);

        log.info("@GetMapping(\"/{type}\")");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new TaskOutputDto(task));
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getCountTask(Authentication authentication){
        User user = userService.findByUsername(authentication.getName());

        Map<TypeTask, Integer> countTask = new TreeMap<>();
        countTask.put(TypeTask.DO_FIRST, taskService.countTaskByType(TypeTask.DO_FIRST, user));
        countTask.put(TypeTask.DO_LATER, taskService.countTaskByType(TypeTask.DO_LATER, user));
        countTask.put(TypeTask.DO_BY_ANOTHER, taskService.countTaskByType(TypeTask.DO_BY_ANOTHER, user));
        countTask.put(TypeTask.DO_NOT_DO, taskService.countTaskByType(TypeTask.DO_NOT_DO, user));

        log.info("@GetMapping(\"/count\")");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(countTask);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskInputDto dto, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());
        System.out.println(dto);
        log.info("@PostMapping(\"/\")");

        Task task = new Task();
        task.setUser(user);
        task.setDescription(dto.getDescription());
        task.setIsDone(dto.getIsDone());
        task.setType(dto.getType());

        taskService.save(task);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new TaskOutputDto(task));
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> refreshTask(@RequestBody RefreshTaskInputDto dto, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());

        log.info("@PutMapping(\"/\")");

        Task task = taskService.findById(dto.getId(), user);
        task.setDescription(dto.getDescription());
        task.setIsDone(dto.getIsDone());
        task.setType(dto.getType());

        taskService.save(task);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new TaskOutputDto(task));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> refreshTask(@PathVariable Long id, Authentication authentication){
        User user = userService.findByUsername(authentication.getName());

        log.info("@DeleteMapping(\"/{id}\")");

        Task task = taskService.findById(id, user);

        taskService.delete(task);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new IdTaskOutputDto(task));
    }
}
