package main.movie.security;

import main.movie.exceptions.AuthenticationException;
import main.movie.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;

    User register(String email, String password);
}
