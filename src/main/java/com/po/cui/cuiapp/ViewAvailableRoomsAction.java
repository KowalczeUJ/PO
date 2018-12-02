package com.po.cui.cuiapp;

import com.po.common.Period;

public class ViewAvailableRoomsAction extends Action{
    ViewAvailableRoomsAction(Display d){
        super(d);
        this.name = "available_rooms";
        this.description = "View available rooms in time period";

    }
    public void action(){
        Period p = Util.getPeriod();
        if(p==null){
            return;
        }
        System.out.println(this.display.hotel.getAvailableRooms(p));
    }

}
