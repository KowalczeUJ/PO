package com.po.user.repository;

import com.po.user.UserDetailsData;

public interface UserDetailsRepository {

    void addUserDetails(UserDetailsData details);

    UserDetailsData getUserDetails(int userId);

}
