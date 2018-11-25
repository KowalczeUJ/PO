package com.po.user.repository;

import com.po.db.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserRepositoryImpl implements UserRepository {

    private Session session;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public boolean isRegular(int userId) {
        Query query = session.createQuery(
                "SELECT 1 FROM User user " +
                    "WHERE EXISTS ( " +
                        "   SELECT 1 FROM User user " +
                        "   WHERE user.id = :userId " +
                        ")"
        );
        query.setParameter("userId", userId);

        return query.uniqueResult() != null;
    }
}
