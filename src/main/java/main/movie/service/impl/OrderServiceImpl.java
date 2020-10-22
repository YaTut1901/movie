package main.movie.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import main.movie.dao.OrderDao;
import main.movie.model.Order;
import main.movie.model.ShoppingCart;
import main.movie.model.Ticket;
import main.movie.model.User;
import main.movie.service.OrderService;
import main.movie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(ShoppingCart cart) { 
        User user = cart.getUser();
        List<Ticket> tickets = new ArrayList<>(cart.getTickets());
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTickets(tickets);
        order.setUser(user);
        shoppingCartService.clear(cart);
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getByUser(user);
    }
}
