package aatsvaeva.webpetproject.mapper;

import aatsvaeva.webpetproject.dto.UserDto;
import aatsvaeva.webpetproject.response.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public abstract List<User> mapItems(List<UserDto> users);

    public abstract User map(UserDto user);
}
