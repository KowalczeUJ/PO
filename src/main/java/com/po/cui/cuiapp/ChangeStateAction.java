package com.po.cui.cuiapp;

public class ChangeStateAction extends Action {
    State target;
    ChangeStateAction(State target){
        this.target = target;
        this.name = target.name;
        this.description = String.format("Go to: >%s< screen",this.name);
    }

    public void action(){
        target.display.setState(target);
    }
}
