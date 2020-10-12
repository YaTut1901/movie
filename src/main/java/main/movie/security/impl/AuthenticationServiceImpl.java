package main.movie.security.impl;

import java.util.Optional;
import main.movie.exceptions.AuthenticationException;
import main.movie.lib.Inject;
import main.movie.lib.Service;
import main.movie.model.User;
import main.movie.security.AuthenticationService;
import main.movie.service.ShoppingCartService;
import main.movie.service.UserService;
import main.movie.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isEmpty()
                || !optionalUser.get().getPassword()
                .equals(HashUtil.hashPassword(password, optionalUser.get().getSalt()))) {
            throw new AuthenticationException("User or password is incorrect!");
        }
        return optionalUser.get();
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
