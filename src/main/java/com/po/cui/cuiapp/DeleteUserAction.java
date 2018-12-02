package com.po.cui.cuiapp;

public class DeleteUserAction extends Action{
    DeleteUserAction(Display d){
        super(d);
        this.name = "delete_account";
        this.description = "Remove users account";
    }

    public void action(){
        this.display.hotel.deleteUser(this.display.currentUser.getId());
        this.display.logout();
    }
}
