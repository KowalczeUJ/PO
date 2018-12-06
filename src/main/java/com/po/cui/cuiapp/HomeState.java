package com.po.cui.cuiapp;

import java.util.HashMap;

public class HomeState  extends State{

    Action login;
    Action logout;
    Action exit;
    Action register;
    Action delete;


    HomeState(Display d){
        super("home",d);
        this.login = new LoginAction(this.display);
        this.logout = new LogoutAction(this.display);
        this.exit = new ExitAction(this.display);
        this.register = new RegisterAction(this.display);
        this.delete = new DeleteUserAction(this.display);
        this.addAction(exit);
    }

    public void prompt(){
        if(this.display.currentUser!=null){
            this.actionMap.remove(this.login.name);
            this.actionMap.remove(this.register.name);
            this.addAction(this.logout);
            this.addAction(this.delete);
        }else{
            this.actionMap.remove(this.logout.name);
            this.actionMap.remove(this.delete.name);
            this.addAction(this.login);
            this.addAction(this.register);
        }
        super.prompt();
    }
}
