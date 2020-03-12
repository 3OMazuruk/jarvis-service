package ua.com.jarvis.controller;

import ua.com.jarvis.domain.Role;
import ua.com.jarvis.domain.User;
import ua.com.jarvis.domain.dto.input.CreateUserInputDto;
import ua.com.jarvis.repository.RoleRepository;
import ua.com.jarvis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PostMapping(value = "/create")
    public void createUser(@Valid @RequestBody CreateUserInputDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setGoogleName("none");
        user.setGoogleUsername("none");
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        userService.create(user);
    }
}
