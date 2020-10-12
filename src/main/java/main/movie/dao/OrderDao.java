package main.movie.dao;

import java.util.List;
import main.movie.model.Order;
import main.movie.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getByUser(User user);
}
