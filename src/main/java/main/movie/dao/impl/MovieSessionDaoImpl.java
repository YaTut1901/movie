package main.movie.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import main.movie.dao.MovieSessionDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.model.MovieSession;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {

    private static final Logger logger = Logger.getLogger(CinemaHallDaoImpl.class);
    private final SessionFactory factory;

    public MovieSessionDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public MovieSession create(MovieSession movieSession) {
        logger.info("MovieSession creating...");
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            logger.info("MovieSession successfully created");
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add movieSession to DB!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession get(Long id) {
        logger.info("MovieSession getting from DB...");
        try (Session session = factory.openSession()) {
            return session.get(MovieSession.class, id);
        }
    }

    @Override
    public List<MovieSession> getAll() {
        logger.info("All MovieSessions getting from DB...");
        try (Session session = factory.openSession()) {
            Query<MovieSession> getAllMovieSessionsQuery = session.createQuery(
                    "from MovieSession", MovieSession.class);
            return getAllMovieSessionsQuery.getResultList();
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        logger.info("Searching for available sessions...");
        try (Session session = factory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> query = criteriaBuilder
                    .createQuery(MovieSession.class);
            Root<MovieSession> root = query.from(MovieSession.class);
            Predicate idPredicate = criteriaBuilder.equal(root.get("movie"), movieId);
            Predicate datePredicate = criteriaBuilder.between(root.get("showTime"),
                    date.atStartOfDay(), date.atTime(LocalTime.MAX));
            return session.createQuery(query.where(idPredicate, datePredicate))
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find available sessions for movieId = "
                    + movieId + " and date " + date, e);
        }
    }
}
