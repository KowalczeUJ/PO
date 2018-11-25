package com.po.user;

import com.po.db.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class HotelUserData extends UserData {

    UserType type;

}
