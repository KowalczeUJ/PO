package com.po.cui.cuiapp;

import com.po.hotel.Hotel;
import com.po.hotel.HotelService;
import com.po.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class CUIApp {
    public void dostuff(Hotel hotel ){

        Display d = new Display(hotel);
        State home = new State("home",d);

        State alt1 = new State("alt1",d);
        State alt2 = new State("alt2",d);

        Action gotoHome = new ChangeStateAction(home);
        Action gotoAlt1 = new ChangeStateAction(alt1);
        Action gotoAlt2 = new ChangeStateAction(alt2);

        home.addAction(gotoAlt1);
        home.addAction(gotoAlt2);
        alt1.addAction(gotoHome);
        alt1.addAction(gotoAlt2);
        alt2.addAction(gotoHome);
        alt2.addAction(gotoAlt1);

        d.setState(home);



    }
}
