package com.po.user.repository;

import com.po.db.user.User;
import com.po.user.HotelUserData;
import com.po.user.UserData;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    com.po.user.User loginUser(UserData userData);

    void addHotelUser(HotelUserData hoteluserData);

    void addBasicUser(UserData userData);

    void deleteUser(int userId);

    boolean isRegular(int userId);

    boolean usernameExists(String username);

    boolean exists(int userId);
}
