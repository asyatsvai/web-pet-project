package aatsvaeva.webpetproject.helper.rowmapper;

import aatsvaeva.webpetproject.dto.UserDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class UserRowMapper implements RowMapper<UserDto> {

    @Override
    public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserDto.builder()
                .id(UUID.fromString(rs.getString("id")))
                .position(rs.getInt("position"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .department(rs.getString("department"))
                .jobPosition(rs.getString("jobPosition"))
                .startPeriod(rs.getDate("startPeriod").toLocalDate())
                .build();
    }
}
