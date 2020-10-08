package main.movie.service.impl;

import java.util.Optional;
import main.movie.dao.UserDao;
import main.movie.lib.Inject;
import main.movie.lib.Service;
import main.movie.model.User;
import main.movie.service.UserService;
import main.movie.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.generateSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
