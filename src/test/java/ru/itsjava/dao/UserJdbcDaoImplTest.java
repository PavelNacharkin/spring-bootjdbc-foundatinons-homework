package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itsjava.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;


@JdbcTest
@Import(UserDaoImpl.class)
public class UserJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "Georg";
    private static final int DEFAULT_AGE = 18;
    private static final long FIRST_ID = 1L;
    private static final long NEW_ID = 4L;

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualUserCount = userDao.count();

        assertEquals(3, actualUserCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        User expectedUser = new User(NEW_ID, DEFAULT_NAME, DEFAULT_AGE);
        userDao.insert(expectedUser);
        User actual = userDao.findById(NEW_ID);

        assertEquals(actual, expectedUser);

    }


    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedUser = new User(FIRST_ID, DEFAULT_NAME, DEFAULT_AGE);
        userDao.update(expectedUser);
        User actual = userDao.findById(FIRST_ID);

        assertEquals(actual, expectedUser);

    }


    @Test
    public void shouldHaveCorrectDelete() {
        User addedUser = new User(NEW_ID, DEFAULT_NAME, DEFAULT_AGE);
        userDao.insert(addedUser);

        assertEquals(userDao.findById(NEW_ID), addedUser);

        userDao.delete(userDao.findById(NEW_ID));
        int result = 1;
        try {
            assertEquals(0, userDao.findById(NEW_ID));
        } catch (EmptyResultDataAccessException e) {
            result = 0;
        }
        assertEquals(0, result);
    }

    @Test
    public void shouldHaveCorrectFindById() {
        User expectedUser = new User(NEW_ID, DEFAULT_NAME, DEFAULT_AGE);
        userDao.insert(expectedUser);


        assertEquals(userDao.findById(NEW_ID), expectedUser);

    }
}
