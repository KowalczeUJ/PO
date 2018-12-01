package com.po.user;

import com.po.db.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    int userId;
    String username;
    UserType type;
    boolean isRegular;

}
