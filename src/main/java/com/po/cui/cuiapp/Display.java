package com.po.cui.cuiapp;

import com.po.hotel.Hotel;
import com.po.hotel.HotelService;
import com.po.user.User;
import com.po.user.UserData;

public class Display {

    public State currentState;
    public Hotel hotel;
    public User currentUser = null;
    Display(Hotel hotel){
        this.hotel = hotel;
    }

    public void setState(State state){
        this.currentState = state;
        this.currentState.onSet();
    }

    public void login(UserData userData){
        this.currentUser = this.hotel.login(userData);
    }
}
