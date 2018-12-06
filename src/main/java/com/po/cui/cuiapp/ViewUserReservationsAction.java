package com.po.cui.cuiapp;

import com.po.reservation.dto.UserReservationDto;

public class ViewUserReservationsAction extends Action {
    ViewUserReservationsAction(Display d){
        super(d);
        this.name="view_user_reservations";
        this.description="View usesr's reservations";
    }

    public void action(){
        for (UserReservationDto element : this.display.hotel.getUserReservations(this.display.currentUser.getId())) {
            System.out.println(element.toString());
        }
        //System.out.println(this.display.hotel.getUserReservations(this.display.currentUser.getId()).toString());
    }
}
