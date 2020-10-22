package main.movie.dao.impl;

import java.util.List;
import main.movie.dao.CinemaHallDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.model.CinemaHall;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private static final Logger logger = Logger.getLogger(CinemaHallDaoImpl.class);
    private SessionFactory factory;

    @Autowired
    public CinemaHallDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public CinemaHall create(CinemaHall cinemaHall) {
        logger.info("CinemaHall creating...");
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            logger.info("CinemaHall successfully created");
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add cinemaHall to DB! " + cinemaHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public CinemaHall get(Long id) {
        logger.info("CinemaHall getting from DB...");
        try (Session session = factory.openSession()) {
            return session.get(CinemaHall.class, id);
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        logger.info("All cinemaHalls getting from DB...");
        try (Session session = factory.openSession()) {
            Query<CinemaHall> getAllCinemaHallsQuery = session.createQuery(
                    "from CinemaHall", CinemaHall.class);
            return getAllCinemaHallsQuery.getResultList();
        }
    }
}
