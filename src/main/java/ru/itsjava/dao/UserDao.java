package ru.itsjava.dao;

import ru.itsjava.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    int count();

    long insert(User user);

    void update(User user);

    void delete(User user);

    Optional<User> findById(long id);
    List<User> findAll();
}
