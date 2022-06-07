package service;

import aatsvaeva.webpetproject.model.User;
import aatsvaeva.webpetproject.repository.UserRepository;
import aatsvaeva.webpetproject.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    @AfterEach
    void noMoreInteractions() {
        verifyNoMoreInteractions(userRepository);
    }


    @Test
    void getUsers() {
        //given
        var users = List.of(new User(UUID.fromString("93befaa8-9c25-449c-b754-5e37fe1bbfcd"), "Asya"));
        doReturn(users).when(userRepository).getUsers();

        //when
        var result = userService.getUsers();

        //then
        verify(userRepository).getUsers();
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getId(), UUID.fromString("93befaa8-9c25-449c-b754-5e37fe1bbfcd"));
        assertEquals(result.get(0).getName(), "Asya");
    }
}
