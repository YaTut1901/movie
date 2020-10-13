package main.movie.service;

import java.util.List;
import main.movie.model.Order;
import main.movie.model.ShoppingCart;
import main.movie.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart cart);

    List<Order> getOrderHistory(User user);
}
