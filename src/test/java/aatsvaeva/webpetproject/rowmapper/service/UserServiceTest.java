package aatsvaeva.webpetproject.rowmapper.service;

import aatsvaeva.webpetproject.dto.UserDto;
import aatsvaeva.webpetproject.mapper.UserMapper;
import aatsvaeva.webpetproject.repository.UserRepository;
import aatsvaeva.webpetproject.response.User;
import aatsvaeva.webpetproject.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserService.class)
@TestPropertySource(properties = {
        "settings.operation.get-users.default-limit=2"
})
class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;
    @MockBean
    UserMapper userMapper;

    @AfterEach
    void noMoreInteractions() {
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(userMapper);
    }


    @Test
    void getUsers() {
        //given
        var userDtos = initUsers();
        doReturn(userDtos).when(userRepository).getUsers(null, 2);
        var users = mapUsers(userDtos);
        doReturn(users).when(userMapper).mapItems(userDtos);

        //when
        var result = userService.getUsers(null, null);

        //then
        verify(userRepository).getUsers(null, 2);
        verify(userMapper).mapItems(userDtos);
        assertEquals(users, result.getItems());
        assertFalse(result.isHasNext());
        assertNull(result.getNextCursor());
    }

    @Test
    void getUsers_empty() {
        //given
        List<UserDto> userDtos = new ArrayList<>(0);
        doReturn(userDtos).when(userRepository).getUsers(null, 2);
        List<User> users = new ArrayList<>(0);
        doReturn(users).when(userMapper).mapItems(userDtos);

        //when
        var result = userService.getUsers(null, 2);

        //then
        verify(userRepository).getUsers(null, 2);
        verify(userMapper).mapItems(userDtos);
        assertEquals(users, result.getItems());
        assertFalse(result.isHasNext());
        assertNull(result.getNextCursor());
    }

    @Test
    void getUsers_hasNext() {
        //given
        var userDtos = initUsers();
        doReturn(userDtos).when(userRepository).getUsers(null, 1);
        var userDtosForResponse = List.of(userDtos.get(0));
        var users = mapUsers(userDtosForResponse);
        doReturn(users).when(userMapper).mapItems(userDtosForResponse);

        //when
        var result = userService.getUsers(null, 1);

        //then
        verify(userRepository).getUsers(null, 1);
        verify(userMapper).mapItems(userDtosForResponse);
        assertEquals(users, result.getItems());
        assertTrue(result.isHasNext());
        assertEquals(userDtosForResponse.get(0).getPosition().toString(), result.getNextCursor());
    }

    @Test
    void getUserMinAgeByDepartment() {
        //given
        var userAge = initUsers().get(0).getAge();
        var department = "invest-social";
        doReturn(userAge).when(userRepository).getMinUserAgeByDepartment(department);

        //when
        var result = userService.getUserMinAgeByDepartment(department);

        //then
        verify(userRepository).getMinUserAgeByDepartment(department);
        assertEquals(userAge, result);
    }

    private List<UserDto> initUsers() {
        UserDto userDto1 = new UserDto(UUID.fromString("93befaa8-9c25-449c-b754-5e37fe1bbfcd"), 1, "Asya",
                "Tsvaeva", 23, "invest-social", "java-developer", LocalDate.now());
        UserDto userDto2 = new UserDto(UUID.fromString("94befaa8-9c25-449c-b754-5e37fe1bbfcd"), 2, "Anastasia",
                "Merenova", 24, "invest-social", "java-developer", LocalDate.now());
        return List.of(userDto1, userDto2);
    }

    private List<User> mapUsers(List<UserDto> userDtos) {
        List<User> users = new ArrayList<>(userDtos.size());
        for (UserDto userDto: userDtos) {
            users.add(new User(userDto.getId(), userDto.getName(), userDto.getSurname(), userDto.getAge(),
                    userDto.getDepartment(), userDto.getJobPosition(), userDto.getStartPeriod()));
        }
        return users;
    }
}
