package main.movie.model.dto.mapper;

import main.movie.model.User;
import main.movie.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto map(User user) {
        return new UserResponseDto(user.getId(),
                user.getEmail(),
                user.getPassword());
    }
}
