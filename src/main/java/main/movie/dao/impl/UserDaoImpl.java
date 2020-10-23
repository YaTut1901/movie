package main.movie.dao.impl;

import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import main.movie.dao.UserDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory factory;

    public UserDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User add(User user) {
        logger.info("User creating...");
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            logger.info("User successfully created");
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user entity!" + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        logger.info("User getting from DB...");
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), email);
            return session.createQuery(query.where(emailPredicate)).uniqueResultOptional();
        }
    }
}
