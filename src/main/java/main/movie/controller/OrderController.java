package main.movie.controller;

import main.movie.model.dto.OrderResponseDto;
import main.movie.model.dto.ShoppingCartRequestDto;
import main.movie.model.dto.mapper.OrderMapper;
import main.movie.model.dto.mapper.ShoppingCartMapper;
import main.movie.service.OrderService;
import main.movie.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartMapper cartMapper;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           UserService userService,
                           ShoppingCartMapper cartMapper,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartMapper = cartMapper;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void complete(ShoppingCartRequestDto dto) {
        orderService.completeOrder(cartMapper.map(dto));
    }

    @GetMapping
    public List<OrderResponseDto> history(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.get(userId)).stream()
                .map(orderMapper::map)
                .collect(Collectors.toList());
    }
}
