package ru.itsjava.services;

import ru.itsjava.domain.User;

import java.util.List;

public interface UserService {
    void insert(User user);
    List<User> findAll();
}
