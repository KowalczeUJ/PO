package com.po.hotel;

import com.po.common.Period;
import com.po.db.room.Room;
import com.po.reservation.ReservationData;
import com.po.reservation.dto.ReservationDto;
import com.po.reservation.dto.UserReservationDto;
import com.po.room.AvailableRooms;

import java.util.List;

public interface Hotel {

    void addRoom(Room room);

    void deleteRoom(String roomNumber);

    void makeReservation(ReservationData data);

    AvailableRooms getAvailableRooms(Period period);

    List<ReservationDto> getAllReservations();

    List<UserReservationDto> getUserReservations(int userId);

}
