package main.movie.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import main.movie.dao.OrderDao;
import main.movie.lib.Inject;
import main.movie.lib.Service;
import main.movie.model.Order;
import main.movie.model.ShoppingCart;
import main.movie.model.Ticket;
import main.movie.model.User;
import main.movie.service.OrderService;
import main.movie.service.ShoppingCartService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

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
