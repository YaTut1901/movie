package main.movie.service.impl;

import java.util.Optional;
import main.movie.dao.UserDao;
import main.movie.lib.Inject;
import main.movie.lib.Service;
import main.movie.model.User;
import main.movie.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.create(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
