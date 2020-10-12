package main.movie.service;

import java.util.List;
import main.movie.model.Order;
import main.movie.model.User;

public interface OrderService {
    Order completeOrder(User user);

    List<Order> getOrderHistory(User user);
}
