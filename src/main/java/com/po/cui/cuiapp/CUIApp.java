package com.po.cui.cuiapp;

import com.po.hotel.Hotel;
import com.po.hotel.HotelService;
import com.po.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class CUIApp {
    public void dostuff(Hotel hotel ){

        Display d = new Display(hotel);
        State home = new HomeState(d);
        State reservation = new ReservationState(d);

        Action gotoHome = new ChangeStateAction(d,home);
        Action goToReservation = new ChangeStateAction(d,reservation);

        home.addAction(goToReservation);
        reservation.addAction(gotoHome);

        d.setState(home);

    }
}
