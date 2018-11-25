package com.po;

import com.po.db.reservation.Reservation;
import com.po.db.room.Room;
import com.po.db.user.User;
import com.po.db.user.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.logging.Level;

import static java.util.logging.Logger.getLogger;

public class SessionFactoryRule implements MethodRule {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private Session session;

    @Override
    public Statement apply(final Statement statement, FrameworkMethod method, Object test) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                sessionFactory = createSessionFactory();
                createSession();
                beginTransaction();
                try {
                    statement.evaluate();
                } finally {
                    shutdown();
                }
            }
        };
    }

    private void shutdown() {
        try {
            try {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(UserDetails.class)
                .addAnnotatedClass(Room.class);
        configuration.setProperty("hibernate.dialect",
                "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class",
                "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem:test");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        getLogger("org.hibernate").setLevel(Level.OFF);

        return configuration.buildSessionFactory();
    }

    public Session createSession() {
        session = sessionFactory.openSession();
        return session;
    }

    public void commit() {
        transaction.commit();
    }

    public void beginTransaction() {
        transaction = session.beginTransaction();
    }

    public SessionFactory getsessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }

}
