package com.po.reservation.repository;

import com.po.db.reservation.Reservation;
import com.po.reservation.dto.ReservationDto;
import com.po.reservation.dto.UserReservationDto;

import java.util.List;

public interface ReservationRepository {

    List<ReservationDto> getAllReservations();

    List<UserReservationDto> getUserReservations(int userId);

    void makeReservation(Reservation reservation);

    void deleteReservation(int reservationId);

}
