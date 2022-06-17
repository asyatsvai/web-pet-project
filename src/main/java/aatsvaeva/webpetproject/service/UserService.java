package aatsvaeva.webpetproject.service;

import aatsvaeva.webpetproject.mapper.UserMapper;
import aatsvaeva.webpetproject.repository.UserRepository;
import aatsvaeva.webpetproject.response.GetUsersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${settings.operation.get-users.default-limit:2}")
    private Integer defaultLimit;

    public GetUsersResponse getUsers(@Nullable String cursor, @Nullable Integer limit) {
        var preparedLimit = limit == null ? defaultLimit : limit;
        var users = userRepository.getUsers(cursor, preparedLimit);
        var hasNext = users.size() > preparedLimit;
        var nextCursor = hasNext ? users.get(preparedLimit - 1).getPosition().toString() : null;
        var items = hasNext ? userMapper.mapItems(users.subList(0, preparedLimit)) : userMapper.mapItems(users);
        return new GetUsersResponse(hasNext, nextCursor, items);
    }

    public Integer getUserMinAgeByDepartment(String department) {
        return userRepository.getMinUserAgeByDepartment(department);
    }
}
