package service;

import aatsvaeva.webpetproject.dto.UserDto;
import aatsvaeva.webpetproject.mapper.UserMapper;
import aatsvaeva.webpetproject.repository.UserRepository;
import aatsvaeva.webpetproject.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserService.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;
    @MockBean
    UserMapper userMapper;

    @AfterEach
    void noMoreInteractions() {
        verifyNoMoreInteractions(userRepository);
    }


//    @Test
//    void getUsers() {
//        //given
//        var users = List.of(new UserDto(UUID.fromString("93befaa8-9c25-449c-b754-5e37fe1bbfcd"),
//                "Asya", "Tsvaeva", 23, "invest-social", "java-developer", LocalDate.now()));
//        doReturn(users).when(userRepository).getUsers();
//
//        //when
//        var result = userService.getUsers();
//
//        //then
//        verify(userRepository).getUsers();
//        assertEquals(result.getItems().size(), 1);
//        assertEquals(result.getItems().get(0).getId(), UUID.fromString("93befaa8-9c25-449c-b754-5e37fe1bbfcd"));
//        assertEquals(result.getItems().get(0).getName(), "Asya");
//    }
}
