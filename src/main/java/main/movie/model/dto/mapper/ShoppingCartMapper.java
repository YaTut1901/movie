package main.movie.model.dto.mapper;

import java.util.stream.Collectors;
import main.movie.model.ShoppingCart;
import main.movie.model.dto.ShoppingCartRequestDto;
import main.movie.model.dto.ShoppingCartResponseDto;
import main.movie.service.ShoppingCartService;
import main.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final UserService userService;
    private final TicketMapper ticketMapper;
    private final ShoppingCartService cartService;

    @Autowired
    public ShoppingCartMapper(UserService userService,
                              TicketMapper ticketMapper,
                              ShoppingCartService cartService) {
        this.userService = userService;
        this.ticketMapper = ticketMapper;
        this.cartService = cartService;
    }

    public ShoppingCartResponseDto map(ShoppingCart cart) {
        return new ShoppingCartResponseDto(cart.getId(),
                cart.getTickets().stream()
                        .map(ticketMapper::map)
                        .collect(Collectors.toList()),
                                cart.getUser().getId());
    }

    public ShoppingCart map(ShoppingCartRequestDto dto) {
        return cartService.getByUser(userService.get(dto.getUserId()));
    }
}
