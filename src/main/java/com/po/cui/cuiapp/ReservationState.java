package com.po.cui.cuiapp;

import com.po.db.user.UserType;

public class ReservationState extends State{

    Action addRoom;
    Action deleteRoom;
    Action viewAvailableRooms;
    Action makeReservation;
    Action viewUserReservations;
    Action viewReservations;


    ReservationState(Display d){
        super("reservation",d);
        this.viewAvailableRooms = new ViewAvailableRoomsAction(d);
        this.viewUserReservations = new ViewUserReservationsAction(d);
        this.viewReservations = new ViewReservationsAction(d);
        this.makeReservation = new MakeReservationAction(d);
        this.addAction(this.viewAvailableRooms);
    }

    public void prompt(){

        if(this.display.currentUser!=null){

            this.addAction(this.viewUserReservations);
            this.addAction(this.makeReservation);

            if(this.display.currentUser.getType()== UserType.ADMIN || this.display.currentUser.getType()== UserType.RECEPTIONIST){

                this.addAction(this.viewReservations);

            }else{

                this.actionMap.remove(this.viewReservations.name);

            }
        }else{
            this.actionMap.remove(this.makeReservation.name);
            this.actionMap.remove(this.viewUserReservations.name);
            this.actionMap.remove(this.viewReservations.name);

        }
        super.prompt();
    }
}
