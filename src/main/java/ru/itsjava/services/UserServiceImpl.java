package ru.itsjava.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itsjava.dao.UserDao;
import ru.itsjava.domain.User;

import java.util.List;
@Data
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public void insert(User user) {
        long id = userDao.insert(user);
        System.out.println("ID new User" + id);

    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
