package main.movie.model.dto.mapper;

import main.movie.model.User;
import main.movie.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public User map(UserResponseDto userResponseDto) {
        User user = new User();
        user.setPassword(userResponseDto.getPassword());
        user.setEmail(userResponseDto.getEmail());
        return user;
    }

    public UserResponseDto map(User user) {
        return new UserResponseDto(user.getEmail(), user.getPassword());
    }
}
