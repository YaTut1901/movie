package main.movie.security.impl;

import java.util.Optional;
import main.movie.exceptions.AuthenticationException;
import main.movie.model.User;
import main.movie.security.AuthenticationService;
import main.movie.service.ShoppingCartService;
import main.movie.service.UserService;
import main.movie.util.HashUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> optionalUser = userService.findByEmail(email);
        logger.warn("User is trying to login...");
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
        logger.info("User have just registered");
        return user;
    }
}
