package main.movie.dao;

import java.util.List;
import java.util.Optional;
import main.movie.model.User;

public interface UserDao {
    User create(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    Optional<User> getByEmail(String email);
}
