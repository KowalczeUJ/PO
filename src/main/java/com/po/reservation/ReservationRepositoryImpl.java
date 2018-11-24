package com.po.reservation;

import com.po.db.reservation.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ReservationRepositoryImpl implements ReservationRepository {

    private Session session;

    public ReservationRepositoryImpl(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    @Override
    public void makeReservation(Reservation reservation) {

    }
}
