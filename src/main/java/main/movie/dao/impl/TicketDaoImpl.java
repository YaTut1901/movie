package main.movie.dao.impl;

import main.movie.dao.TicketDao;
import main.movie.exceptions.DataProcessingException;
import main.movie.lib.Dao;
import main.movie.model.Ticket;
import main.movie.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
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
