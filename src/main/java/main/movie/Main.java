package main.movie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import main.movie.lib.Injector;
import main.movie.model.CinemaHall;
import main.movie.model.Movie;
import main.movie.model.MovieSession;
import main.movie.model.ShoppingCart;
import main.movie.model.User;
import main.movie.security.AuthenticationService;
import main.movie.service.CinemaHallService;
import main.movie.service.MovieService;
import main.movie.service.MovieSessionService;
import main.movie.service.ShoppingCartService;
import main.movie.service.UserService;

public class Main {

    private static Injector injector = Injector.getInstance("main.movie");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie1 = new Movie();
        movie1.setTitle("Movie1");
        movieService.add(movie1);
        Movie movie2 = new Movie();
        movie2.setTitle("Movie2");
        movieService.add(movie2);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setDescription("HALL NUMBER ONE");
        cinemaHallService.add(cinemaHall1);
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setDescription("HALL NUMBER TWO");
        cinemaHallService.add(cinemaHall2);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession1 = new MovieSession();
        movieSession1.setCinemaHall(cinemaHall1);
        movieSession1.setMovie(movie1);
        movieSession1.setShowTime(LocalDateTime.of(2020, 10,
                20, 15, 30));

        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);

        movieSessionService.add(movieSession1);
        MovieSession movieSession2 = new MovieSession();
        movieSession2.setCinemaHall(cinemaHall2);
        movieSession2.setMovie(movie2);
        movieSession2.setShowTime(LocalDateTime.of(2020, 10,
                22, 15, 31));
        movieSessionService.add(movieSession2);
        System.out.println(movieSessionService
                .findAvailableSessions(1L, LocalDate.of(2020,
                        10, 20)));

        System.out.println(movieSessionService
                .findAvailableSessions(2L, LocalDate.of(2020,
                        10, 22)));

        ShoppingCartService shoppingCartService
                = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        AuthenticationService authenticationService
                = (AuthenticationService) injector.getInstance(AuthenticationService.class);
        UserService userService
                = (UserService) injector.getInstance(UserService.class);

        User user = authenticationService.register("email", "1234");
        System.out.println(userService.findByEmail("myemdfail"));

        ShoppingCart cart = shoppingCartService.getByUser(user);
        System.out.println(cart);
        shoppingCartService.addSession(movieSession1, user);
        shoppingCartService.clear(cart);
        System.out.println(shoppingCartService.getByUser(user));
    }
}
