package aatsvaeva.webpetproject.service;

import aatsvaeva.webpetproject.model.User;
import aatsvaeva.webpetproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
