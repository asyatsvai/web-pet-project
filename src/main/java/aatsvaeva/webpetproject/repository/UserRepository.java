package aatsvaeva.webpetproject.repository;

import aatsvaeva.webpetproject.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {
        var sql = "SELECT id, name " +
                "FROM common.user " +
                "LIMIT 100;";
        return jdbcTemplate.query(sql, (resultSet, i) -> new User(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("name")
        ));
    }
}
