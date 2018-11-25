package com.po.user.repository;

import com.po.db.user.User;

public interface UserRepository {

    void addUser(User user);

    void deleteUser(int userId);

    boolean isRegular(int userId);
}
