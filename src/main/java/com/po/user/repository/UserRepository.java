package com.po.user.repository;

import com.po.user.User;
import com.po.user.UserData;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User loginUser(UserData userData);

    int addHotelUser(UserData UserData);

    int addBasicUser(UserData userData);

    void deleteUser(int userId);

    boolean isRegular(int userId);

    boolean usernameExists(String username);

    boolean exists(int userId);
}
