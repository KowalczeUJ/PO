package com.po.hotel;

import com.po.db.reservation.Reservation;
import com.po.db.room.Room;
import com.po.common.Period;
import com.po.reservation.ReservationRepository;
import com.po.reservation.ReservationRepositoryImpl;
import com.po.room.AvailableRooms;
import com.po.room.RoomRepository;
import com.po.room.RoomRepositoryImpl;
import com.po.room.RoomView;
import org.hibernate.SessionFactory;

import java.util.List;

public class HotelService implements Hotel {

    private final SessionFactory sessionFactory;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public HotelService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        reservationRepository = new ReservationRepositoryImpl(this.sessionFactory);
        roomRepository = new RoomRepositoryImpl(this.sessionFactory);
    }

    @Override
    public void addRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(String roomNumber) {
        roomRepository.delete(roomNumber);
    }

    @Override
    public void makeReservation(Reservation reservation) {
        reservationRepository.makeReservation(reservation);
    }

    @Override
    public AvailableRooms getAvailableRooms(Period period) {
        List<RoomView> rooms = roomRepository.getAvailableRooms(period);

        return AvailableRooms.builder()
                .period(period)
                .rooms(rooms)
                .build();
    }

}
