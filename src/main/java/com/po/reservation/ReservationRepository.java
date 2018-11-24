package com.po.reservation;

import java.util.List;

public interface ReservationRepository {

    List<ReservationDto> getAllReservations();

    List<UserReservationDto> getUserReservations(int userId);

    void makeReservation(com.po.db.reservation.Reservation reservation);

    void deleteReservation(int reservationId);

}
