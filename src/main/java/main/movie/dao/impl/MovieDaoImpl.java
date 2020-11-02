package main.movie.dao.impl;

import java.util.List;
import main.movie.dao.MovieDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.model.Movie;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {

    private static final Logger logger = Logger.getLogger(MovieDaoImpl.class);
    private final SessionFactory factory;

    @Autowired
    public MovieDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Movie add(Movie movie) {
        logger.info("Movie creating...");
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            logger.info("Movie successfully created");
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie entity!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Movie get(Long id) {
        logger.info("Movie getting from DB...");
        try (Session session = factory.openSession()) {
            return session.get(Movie.class, id);
        }
    }

    @Override
    public List<Movie> getAll() {
        logger.info("All Movies getting from DB...");
        try (Session session = factory.openSession()) {
            Query<Movie> getAllMoviesQuery = session.createQuery("from Movie", Movie.class);
            return getAllMoviesQuery.getResultList();
        }
    }
}
