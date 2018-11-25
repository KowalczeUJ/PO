package com.po.hotel;

import com.po.common.Period;
import com.po.db.room.Room;
import com.po.reservation.ReservationData;
import com.po.reservation.dto.ReservationDto;
import com.po.reservation.ReservationService;
import com.po.reservation.dto.UserReservationDto;
import com.po.reservation.repository.ReservationRepository;
import com.po.reservation.repository.ReservationRepositoryImpl;
import com.po.room.AvailableRooms;
import com.po.room.RoomData;
import com.po.room.RoomView;
import com.po.room.repository.RoomRepository;
import com.po.room.repository.RoomRepositoryImpl;
import com.po.user.repository.UserRepository;
import com.po.user.repository.UserRepositoryImpl;
import org.hibernate.SessionFactory;

import java.util.List;

public class HotelService implements Hotel {

    private final SessionFactory sessionFactory;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public HotelService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        reservationRepository = new ReservationRepositoryImpl(this.sessionFactory);
        roomRepository = new RoomRepositoryImpl(this.sessionFactory);
        userRepository = new UserRepositoryImpl(this.sessionFactory);
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
    public void makeReservation(ReservationData reservationData) {
        RoomData roomData = roomRepository.getRoomDataForId(reservationData.getRoomId());
        reservationData = reservationData.toBuilder()
                .isRegular(userRepository.isRegular(reservationData.getUserId()))
                .build();

        ReservationService service = new ReservationService(reservationRepository, roomData, reservationData);
        service.makeReservation();
    }

    @Override
    public AvailableRooms getAvailableRooms(Period period) {
        List<RoomView> rooms = roomRepository.getAvailableRooms(period);

        return AvailableRooms.builder()
                .period(period)
                .rooms(rooms)
                .build();
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.getAllReservations();
    }

    @Override
    public List<UserReservationDto> getUserReservations(int userId) {
        return reservationRepository.getUserReservations(userId);
    }

}
