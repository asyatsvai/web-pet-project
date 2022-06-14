package aatsvaeva.webpetproject.repository;

import aatsvaeva.webpetproject.helper.rowmapper.UserRowMapper;
import aatsvaeva.webpetproject.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public List<UserDto> getUsers(@Nullable String cursor, Integer limit) {
        var sql = "SELECT u.id, u.position, u.name, u.surname, EXTRACT(year FROM age(current_date, u.birth_date)) as age, " +
                "e.department, e.position as jobPosition, lower(e.period) as startPeriod " +
                "FROM common.user u " +
                "JOIN common.employment e " +
                "ON u.employment_id = e.id " +
                "WHERE (:cursor IS NULL OR u.position < :cursor) " +
                "ORDER BY u.position DESC " +
                "LIMIT :limit";
        var params = new MapSqlParameterSource();
        params.addValue("limit", limit + 1);
        params.addValue("cursor", cursor, Types.BIGINT);
        return jdbcTemplate.query(sql, params, userRowMapper);
    }

    public Integer getMinUserAgeByDepartment(String department) {
        var sql = "SELECT min(EXTRACT(year FROM age(current_date, u.birth_date))) " +
                "FROM common.user u " +
                "JOIN common.employment e " +
                "ON u.employment_id = e.id " +
                "GROUP BY e.department " +
                "HAVING e.department = :department";
        var params = new MapSqlParameterSource("department", department);
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }
}
