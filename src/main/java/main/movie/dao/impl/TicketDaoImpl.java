package main.movie.dao.impl;

import main.movie.dao.TicketDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.model.Ticket;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl implements TicketDao {

    private static final Logger logger = Logger.getLogger(TicketDaoImpl.class);
    private final SessionFactory factory;

    public TicketDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        logger.info("Ticket creating...");
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            logger.info("Ticket successfully created");
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add ticket to DB! " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
