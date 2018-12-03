package com.po.user.repository;

import com.po.db.user.User;
import com.po.db.user.UserType;
import com.po.user.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Session session;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    @Override
    public int addHotelUser(UserData userData) {
        User hotelUser = User.builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
                .type(userData.getType())
                .isRegular(true)
                .createdOn(LocalDateTime.now())
                .build();

        session.save(hotelUser);

        return hotelUser.getId();
    }

    @Override
    public int addBasicUser(UserData userData) {
        User user = User.builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
                .type(UserType.BASIC)
                .isRegular(false)
                .createdOn(LocalDateTime.now())
                .build();

        session.save(user);

        return user.getId();
    }

    @Override
    public void deleteUser(int userId) {
        session.beginTransaction();
        session.createQuery("DELETE FROM User WHERE id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public boolean isRegular(int userId) {
        Query query = session.createQuery(
                "SELECT (CASE " +
                        "   WHEN user.isRegular = 1 THEN TRUE " +
                        "   ELSE FALSE " +
                        "   END " +
                        ") " +
                        "FROM User user " +
                        "WHERE user.id = :userId "
        );
        query.setParameter("userId", userId);

        return (boolean) query.uniqueResult();
    }

    @Override
    public boolean usernameExists(String username) {
        return session.createQuery(
                "SELECT 1 FROM User user " +
                    "WHERE EXISTS (" +
                    "   SELECT 1 FROM User user " +
                    "   WHERE user.username = :username)")
                .setParameter("username", username)
                .uniqueResult() != null;
    }

    @Override
    public boolean exists(int userId) {
        return session.createQuery(
                "SELECT 1 FROM User user " +
                    "WHERE EXISTS (" +
                    "   SELECT 1 FROM User user " +
                    "   WHERE user.id = :userId)")
                .setParameter("userId", userId)
                .uniqueResult() != null;
    }

    @Override
    public User getUserForId(int userId) {
        return session.createQuery("FROM User WHERE id = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public com.po.user.User loginUser(UserData userData) {
        boolean exists = session.createQuery(
                "SELECT 1 FROM User user " +
                    "WHERE EXISTS (" +
                    "   SELECT 1 FROM User user " +
                    "   WHERE user.username = :username" +
                    "   AND user.password = :password)")
                .setParameter("username", userData.getUsername())
                .setParameter("password", userData.getPassword())
                .uniqueResult() != null;
        if (exists) {
            return getUserData(userData.getUsername());
        } else {
            return null;
        }
    }

    private com.po.user.User getUserData(String username) {
        Query query = session.createQuery(
                "SELECT new com.po.user.User(" +
                        "user.id, " +
                        "user.username, " +
                        "user.type, " +
                        "user.isRegular " +
                        ")" +
                    "FROM User user " +
                    "WHERE user.username = :username ",
                com.po.user.User.class);
        query.setParameter("username", username);

        return (com.po.user.User) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<com.po.user.User> getAllUsers() {
        return session.createQuery("SELECT new com.po.user.User(" +
                "user.id, " +
                "user.username, " +
                "user.type, " +
                "user.isRegular " +
                ") " +
                "FROM User user"
        ).getResultList();
    }



}
