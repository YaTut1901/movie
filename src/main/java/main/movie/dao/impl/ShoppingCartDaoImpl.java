package main.movie.dao.impl;

import main.movie.dao.ShoppingCartDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.model.ShoppingCart;
import main.movie.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    private static final Logger logger = Logger.getLogger(CinemaHallDaoImpl.class);
    private SessionFactory factory;

    @Autowired
    public ShoppingCartDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        logger.info("ShoppingCart creating...");
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            logger.info("ShoppingCart successfully created");
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add shoppingCart to DB! " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        logger.info("ShoppingCart getting from DB...");
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM ShoppingCart cart "
                            + "left join fetch cart.tickets "
                            + "join fetch cart.user WHERE cart.user = :user",
                    ShoppingCart.class)
                    .setParameter("user", user)
                    .uniqueResult();
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        logger.info("ShoppingCart updating...");
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shoppingCart! " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
