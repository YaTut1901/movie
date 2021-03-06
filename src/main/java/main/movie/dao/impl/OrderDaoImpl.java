package main.movie.dao.impl;

import java.util.List;
import main.movie.dao.OrderDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.model.Order;
import main.movie.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private final SessionFactory factory;

    public OrderDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Order add(Order order) {
        logger.info("Order creating...");
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            logger.info("Order successfully created");
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add order to DB! " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getByUser(User user) {
        logger.info("Orders getting from DB...");
        try (Session session = factory.openSession()) {
            return session.createQuery("SELECT DISTINCT orders FROM Order orders "
                    + "left join fetch orders.tickets "
                    + "WHERE orders.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        }
    }
}
