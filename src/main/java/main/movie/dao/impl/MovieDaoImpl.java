package main.movie.dao.impl;

import java.util.List;
import main.movie.dao.MovieDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.lib.Dao;
import main.movie.model.Movie;
import main.movie.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImpl implements MovieDao {

    private static final Logger logger = Logger.getLogger(CinemaHallDaoImpl.class);

    @Override
    public Movie add(Movie movie) {
        logger.info("Movie creating...");
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
    public List<Movie> getAll() {
        logger.info("All Movies getting from DB...");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> getAllMoviesQuery = session.createQuery("from Movie", Movie.class);
            return getAllMoviesQuery.getResultList();
        }
    }
}
