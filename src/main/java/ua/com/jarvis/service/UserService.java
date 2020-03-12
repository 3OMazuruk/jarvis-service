package ua.com.jarvis.service;

import ua.com.jarvis.domain.User;

public interface UserService {

    void create(User user);

    User findByUsername(String username);

}
