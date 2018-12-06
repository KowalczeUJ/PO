package com.po.cui.cuiapp;

import com.po.db.user.User;
import com.po.db.user.UserType;
import com.po.user.UserData;

public class LoginAction extends Action {
    LoginAction(Display d){
        super(d);
        this.name = "login";
        this.description = "Log in to the system";

    }
    public void action(){
        System.out.println("Please provide username:");
        String username = State.scanner.next().trim();
        System.out.println("Please provide user password:");
        String pass = State.scanner.next().trim();
        System.out.println("Please provide user type (number): 0:Admin,1:Receptionist,2:Basic ");
        int type = State.scanner.nextInt();
        if(type < 0 || type >2){
            System.out.println("Number not within range");
            return;
        }
        UserType ut = UserType.values()[type];
        UserData ud = new UserData(username,pass,ut);

        this.display.login(ud);
    }
}
