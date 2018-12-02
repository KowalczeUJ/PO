package com.po.cui.cuiapp;

public abstract class Action {
    String name = "";
    String description = "";
    Display display = null;
    Action(Display d){
        this.display = d;
    }

    abstract void action();
}
