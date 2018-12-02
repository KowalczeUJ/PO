package com.po.cui.cuiapp;

import com.po.user.User;
import com.po.user.UserData;

public class RegisterAction extends Action{
    RegisterAction(Display d){
        super(d);
        this.name ="register";
        this.description = "Register new user";
    }

    public void action(){
        UserData ud = Util.getUserData();
        if(ud!=null){
            this.display.hotel.registerHotelUser(ud);
        }
    }
}
