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

    public String toString(){
        String reg = " (regular customer)";
        if(!isRegular){
            reg = "";
        }
        return String.format("%s:%s%s",username,type,reg);
    }

}
