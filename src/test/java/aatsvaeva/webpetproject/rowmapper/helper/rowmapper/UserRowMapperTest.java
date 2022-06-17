package aatsvaeva.webpetproject.rowmapper.helper.rowmapper;

import aatsvaeva.webpetproject.helper.rowmapper.UserRowMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserRowMapper.class)
class UserRowMapperTest {

    @Autowired
    UserRowMapper userRowMapper;

    UUID userId = UUID.randomUUID();
    int position = 1;
    String userName = "Asya";
    String userSurname = "Tsvaeva";
    int userAge = 23;
    String department = "invest-social";
    String jobPosition = "java-developer";
    LocalDate startPeriod = LocalDate.now();

    @Test
    void mapRow() throws SQLException {
        //given
        var resultSet = givenResultSet();

        //when
        var result = userRowMapper.mapRow(resultSet, 0);

        //then
        assertEquals(userId, result.getId());
        assertEquals(position, result.getPosition());
        assertEquals(userName, result.getName());
        assertEquals(userSurname, result.getSurname());
        assertEquals(userAge, result.getAge());
        assertEquals(department, result.getDepartment());
        assertEquals(jobPosition, result.getJobPosition());
        assertEquals(startPeriod, result.getStartPeriod());
    }

    private ResultSet givenResultSet() throws SQLException {
        var resultSet = mock(ResultSet.class);
        doReturn(userId.toString()).when(resultSet).getString("id");
        doReturn(position).when(resultSet).getInt("position");
        doReturn(userName).when(resultSet).getString("name");
        doReturn(userSurname).when(resultSet).getString("surname");
        doReturn(userAge).when(resultSet).getInt("age");
        doReturn(department).when(resultSet).getString("department");
        doReturn(jobPosition).when(resultSet).getString("jobPosition");
        doReturn(Date.valueOf(startPeriod)).when(resultSet).getDate("startPeriod");
        return resultSet;
    }
}