package com.po.hotel;

import com.po.db.reservation.Reservation;
import com.po.db.room.Room;
import com.po.common.Period;
import com.po.room.AvailableRooms;

public interface Hotel {

    void addRoom(Room room);

    void deleteRoom(String roomNumber);

    void makeReservation(Reservation reservation);

    AvailableRooms getAvailableRooms(Period period);

}
