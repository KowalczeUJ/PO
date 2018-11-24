package com.po.user;

import com.po.db.user.User;
import org.hibernate.Session;

public class UserRepositoryImpl implements UserRepository {

    private Session session;

    public UserRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(int userId) {

    }
}
