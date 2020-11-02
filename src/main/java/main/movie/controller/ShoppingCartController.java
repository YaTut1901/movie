package main.movie.controller;

import main.movie.model.dto.MovieSessionRequestDto;
import main.movie.model.dto.ShoppingCartResponseDto;
import main.movie.model.dto.mapper.MovieSessionMapper;
import main.movie.model.dto.mapper.ShoppingCartMapper;
import main.movie.service.ShoppingCartService;
import main.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionMapper sessionMapper;
    private final ShoppingCartMapper cartMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionMapper sessionMapper,
                                  ShoppingCartMapper cartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.sessionMapper = sessionMapper;
        this.cartMapper = cartMapper;
    }

    @PostMapping("/movie-sessions")
    public void addSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto,
                           @RequestParam Long userId) {
        shoppingCartService.addSession(sessionMapper.map(movieSessionRequestDto),
                userService.get(userId));
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto get(@RequestParam Long userId) {
        return cartMapper.map(shoppingCartService.getByUser(userService.get(userId)));
    }
}
