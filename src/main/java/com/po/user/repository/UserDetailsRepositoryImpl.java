package com.po.user.repository;

import com.po.db.user.UserDetails;
import com.po.user.UserDetailsData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserDetailsRepositoryImpl implements UserDetailsRepository {

    private Session session;

    public UserDetailsRepositoryImpl(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    @Override
    public void addUserDetails(UserDetails details) {
        session.save(details);
    }

    @Override
    public UserDetailsData getUserDetails(int userId) {
        Query query = session.createQuery(
                "SELECT new com.po.user.UserDetailsData(" +
                        "details.user.id, " +
                        "details.address, " +
                        "details.city, " +
                        "details.phoneNumber, " +
                        "details.birthDate " +
                        ")" +
                        "FROM UserDetails details " +
                        "WHERE details.user.id = :userId",
                UserDetailsData.class);
        query.setParameter("userId", userId);
        return (UserDetailsData) query.getSingleResult();
    }

}
