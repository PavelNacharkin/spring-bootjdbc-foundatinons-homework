package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@JdbcTest
@Import(UserDaoImpl.class)
public class UserJdbcDaoImplTest {
    private static final String DEFAULT_NAME = "Georg";
    private static final int DEFAULT_AGE = 18;

    private static final long FIRST_ID = 1L;
    private static final long NEW_ID = 4L;
    private static final Pet DEFAULT_PET = new Pet(1L, "Maine Coon");

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
        Optional<User> actualUser = userDao.findById(idFromDB);

        assertAll(() -> assertEquals(actualUser.get().getName(), expectedUser.getName()),
                () -> assertEquals(actualUser.get().getAge(), expectedUser.getAge()));

    }


    @Test
    public void shouldHaveCorrectUpdate() {
        User expectedUser = new User(FIRST_ID, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
        userDao.update(expectedUser);
        Optional<User> actualUser = userDao.findById(FIRST_ID);

        assertEquals(actualUser.get(), expectedUser);

    }


    @Test
    public void shouldHaveCorrectDelete() {
        User addedUser = new User(NEW_ID, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
        userDao.insert(addedUser);

        Optional<User> userFromDB = userDao.findById(NEW_ID);
        assertTrue(userFromDB.isPresent());

        userDao.delete(userFromDB.get());

        Optional<User> userAfterDeleteFromDB = userDao.findById(NEW_ID);
        assertFalse(userAfterDeleteFromDB.isPresent());


    }

    @Test
    public void shouldHaveCorrectFindById() {
        User expectedUser = new User(NEW_ID, DEFAULT_NAME, DEFAULT_AGE, DEFAULT_PET);
        userDao.insert(expectedUser);

        Optional<User> actualUser = userDao.findById(NEW_ID);
        assertTrue(actualUser.isPresent());
        assertEquals(actualUser.get(), expectedUser);


    }

    @Test
    public void shouldHaveCorrectFindAll() {
        List<User> userList = userDao.findAll();
        assertEquals(userDao.count(), userList.size());

    }
}
