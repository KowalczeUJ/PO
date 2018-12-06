package com.po.cui.cuiapp;

import java.util.HashMap;
import java.util.Scanner;

public class State {

    public String name;
    Display display;
    HashMap<String,Action> actionMap = new HashMap<String,Action>();
    static Scanner scanner = new Scanner(System.in);

    State(String name,Display display){
        this.name = name;
        this.display = display;

    }
    public void onSet(){
        prompt();
    }

    public void addAction(Action action){
        this.actionMap.put(action.name,action);
    }

    public void prompt(){
        String cuser = "--";
        if(this.display.currentUser != null){
            cuser = this.display.currentUser.toString();
        }
        String prompto = String.format("> %s %s",this.name,cuser);

        System.out.println(prompto);
        for(String s : actionMap.keySet()){
            Action a = actionMap.get(s);
            System.out.println(String.format("%s : %s",a.name,a.description));
        }
        chooseAction();
    }

    private void chooseAction(){
        String str = scanner.next().trim();
        if(!actionMap.keySet().contains(str)){
            System.out.println(String.format("%s is not one of the available actions, try again",str));
            prompt();
        } else {
            actionMap.get(str).action();
            if(display.currentState==this){
                prompt();
            }
        }
    }


}
