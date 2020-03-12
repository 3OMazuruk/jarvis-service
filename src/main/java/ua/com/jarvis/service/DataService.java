package ua.com.jarvis.service;

import ua.com.jarvis.domain.User;

import java.util.List;

public interface DataService<T> {

    T save(T entity);

    T findById(Long id, User user);

    List<T> findAll(User user);

    void delete(T entity);

}
