package main.movie.dao;

import java.util.Optional;
import main.movie.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
