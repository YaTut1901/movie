package main.movie.dao;

import main.movie.model.ShoppingCart;
import main.movie.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}


