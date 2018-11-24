package com.po.user;

import com.po.db.user.User;

public interface UserRepository {

    void addUser(User user);

    void deleteUser(int userId);
}
