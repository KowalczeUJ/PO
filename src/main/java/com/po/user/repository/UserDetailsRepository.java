package com.po.user.repository;

import com.po.db.user.UserDetails;
import com.po.user.UserDetailsData;

public interface UserDetailsRepository {

    void addUserDetails(UserDetails details);

    UserDetailsData getUserDetails(int userId);

}
