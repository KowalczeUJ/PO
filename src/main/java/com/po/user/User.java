package com.po.user;

import com.po.db.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    String username;
    UserType type;

}
