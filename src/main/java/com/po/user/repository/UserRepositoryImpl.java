package com.po.user.repository;

import com.po.db.user.User;
import com.po.db.user.UserType;
import com.po.user.HotelUserData;
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
    public void addHotelUser(HotelUserData hotelUserData) {
        User hotelUser = User.builder()
                .username(hotelUserData.getUsername())
                .password(hotelUserData.getPassword())
                .type(hotelUserData.getType())
                .isRegular(true)
                .createdOn(LocalDateTime.now())
                .build();
        session.save(hotelUser);
    }

    @Override
    public void addBasicUser(UserData userData) {
        User user = User.builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
                .type(UserType.BASIC)
                .isRegular(false)
                .createdOn(LocalDateTime.now())
                .build();
        session.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        session.createQuery("DELETE FROM User WHERE id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public boolean isRegular(int userId) {
        Query query = session.createQuery(
                "SELECT 1 FROM User user " +
                    "WHERE EXISTS ( " +
                        "   SELECT 1 FROM User user " +
                        "   WHERE user.id = :userId)"
        );
        query.setParameter("userId", userId);

        return query.uniqueResult() != null;
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
                    "   WHERE user.username = :userId)")
                .setParameter("userId", userId)
                .uniqueResult() != null;
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
                        "user.type )" +
                    "FROM User user " +
                    "WHERE user.username = :username ",
                com.po.user.User.class);
        query.setParameter("username", username);

        return (com.po.user.User) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return session.createQuery("FROM User")
                .getResultList();
    }

}
