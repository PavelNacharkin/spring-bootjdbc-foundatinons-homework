package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public void insert(User user) {
        Map<String, Object> params = Map.of("name", user.getName(), "age", user.getAge());
        jdbc.update("insert into users(name, age) values(:name, :age)", params);
    }

    @Override
    public void update(User user) {
        Map<String, Object> params = Map.of("id", user.getId(), "name", user.getName(), "age", user.getAge());

        jdbc.update("update users set name = :name where id = :id", params);
        jdbc.update("update users set age = :age where id = :id", params);
    }

    @Override
    public void delete(User user) {
        Map<String, Object> params = Map.of("id", user.getId());
        jdbc.update("delete from users where id = :id", params);
    }

    @Override
    public User findById(long id) {
        Map<String, Object> params = Map.of("id", id);
        return jdbc.queryForObject("select id, name, age from users where id = :id", params, new UsersMapper());
    }

    private static class UsersMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getLong("id"), rs.getString("name"), rs.getInt("age"));
        }
    }
}
