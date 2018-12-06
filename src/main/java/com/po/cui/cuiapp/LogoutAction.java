package com.po.cui.cuiapp;

public class LogoutAction extends Action{
    LogoutAction(Display d){
        super(d);
        this.name = "logout";
        this.description = "Log out";
    }

    public void action(){

        this.display.logout();
    }
}
