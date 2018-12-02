package com.po.cui.cuiapp;

public class ChangeStateAction extends Action {
    State target;
    public ChangeStateAction(Display d, State target){
        super(d);
        this.target = target;
        this.name = target.name;
        this.description = String.format("Go to: >%s< screen",this.name);
    }

    public void action(){
        this.display.setState(target);
    }
}
