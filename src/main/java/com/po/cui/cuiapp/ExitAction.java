package com.po.cui.cuiapp;

public class ExitAction extends Action {
    ExitAction(Display d){
        super(d);
        this.name = "exit";
        this.description = "Exit program";
    }

    public void action(){
        this.display.exit();
    }
}
