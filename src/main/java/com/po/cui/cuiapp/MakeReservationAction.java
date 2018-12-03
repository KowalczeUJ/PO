package com.po.cui.cuiapp;

import com.po.common.Period;
import com.po.reservation.ReservationData;

import java.time.LocalDate;

public class MakeReservationAction extends Action {
    MakeReservationAction(Display d){
        super(d);
        this.name = "make_reservation";
        this.description = "Make reservation";
    }

    public void action(){
        System.out.println("Please provide room id:");
        Integer id = Util.getInt(0,1000000);
        if(id==null){System.out.println("Could not finish reservation process");return;}
        System.out.println("Please provide number of guests:");
        Integer num = Util.getInt(0,1000000);
        if(num==null){System.out.println("Could not finish reservation process");return;}
        System.out.println("Please provide period of reservation:");
        Period p = Util.getPeriod();
        if(p==null){System.out.println("Could not finish reservation process");return;}
        ReservationData data = new ReservationData(
                this.display.currentUser.getId(),
                this.display.currentUser.getRegular(),
                id,
                num,
                p.getStartDate(),
                p.getEndDate());
        this.display.hotel.makeReservation(data);

    }
}
