package main.movie.dao.impl;

import main.movie.dao.ShoppingCartDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.lib.Dao;
import main.movie.model.ShoppingCart;
import main.movie.model.User;
import main.movie.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
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
}
