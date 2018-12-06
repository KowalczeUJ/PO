package com.po.cui.cuiapp;

import com.po.reservation.dto.ReservationDto;

public class ViewReservationsAction extends Action {
    ViewReservationsAction(Display d){
        super(d);
        this.name="view_all_reservations";
        this.description="View all reservations";
    }

    public void action(){
        for (ReservationDto element : this.display.hotel.getAllReservations()) {
            System.out.println(element.toString());
        }
        //System.out.println(this.display.hotel.getAllReservations().toString());
    }
}
