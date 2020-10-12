package main.movie.service;

import main.movie.model.MovieSession;
import main.movie.model.ShoppingCart;
import main.movie.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
