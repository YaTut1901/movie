package main.movie.service;

import java.util.Optional;
import main.movie.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    User get(Long id);
}
