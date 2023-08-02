package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@JdbcTest
@Import(UserDaoImpl.class)
public class UserJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "Georg";
    private static final int DEFAULT_AGE = 18;

    private static final long FIRST_ID = 1L;
    private static final long NEW_ID = 4L;
    private static final Pet DEFAULT_PET = new Pet(1L,"Maine Coon");

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualUserCount = userDao.count();

        assertEquals(3, actualUserCount);
    }

    @Test
    public void shouldHaveCorrectInsert() {
        User expectedUser = new User(DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);

        long idFromDB = userDao.insert(expectedUser);
        User actualUser = userDao.findById(idFromDB);

        assertAll(() -> assertEquals(actualUser.getName(), expectedUser.getName()),
                () -> assertEquals(actualUser.getAge(), expectedUser.getAge()));

    }


    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedUser = new User(FIRST_ID, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
        userDao.update(expectedUser);
        User actual = userDao.findById(FIRST_ID);

        assertEquals(actual, expectedUser);

    }


    @Test
    public void shouldHaveCorrectDelete() {
        User addedUser = new User(NEW_ID, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
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
        User expectedUser = new User(NEW_ID, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
        userDao.insert(expectedUser);


        assertEquals(userDao.findById(NEW_ID), expectedUser);

    }
}
