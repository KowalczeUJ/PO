package com.po.reservation;

import com.po.db.reservation.Reservation;

public interface ReservationRepository {

    void makeReservation(Reservation reservation);

}
