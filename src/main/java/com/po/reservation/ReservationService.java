package com.po.reservation;

import com.po.db.reservation.Reservation;
import com.po.db.room.Room;
import com.po.db.user.User;
import com.po.reservation.repository.ReservationRepository;
import com.po.room.RoomData;
import com.po.room.repository.RoomRepository;
import com.po.user.repository.UserRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class ReservationService {

    private final RoomData roomData;
    private final ReservationData reservationData;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              RoomData roomData,
                              ReservationData reservationData,
                              UserRepository userRepository,
                              RoomRepository roomRepository
    ) {
        this.roomData = roomData;
        this.reservationData = reservationData;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public void makeReservation() {
        BigDecimal totalCost = getTotalReservationCost();

        Reservation reservation = Reservation.builder()
                .user(userRepository.getUserForId(reservationData.getUserId()))
                .room(roomRepository.getRoomForId(roomData.getId()))
                .price(totalCost)
                .persons(reservationData.getPersons())
                .startDate(reservationData.getStartDate())
                .endDate(reservationData.getEndDate())
                .createdOn(LocalDateTime.now())
                .build();

        reservationRepository.makeReservation(reservation);
    }

    private BigDecimal getTotalReservationCost() {
        boolean isRegular = reservationData.isRegular();
        long totalDays = DAYS.between(reservationData.getStartDate(), reservationData.getEndDate());

        TotalCost totalCost = new TotalCost(totalDays, roomData);

        totalCost.getBaseCost();
        totalCost.getUpFrontDiscount(reservationData.getStartDate());

        if (isRegular) {
            totalCost.getRegularUserDiscount();
        }

        return totalCost.getTotalCost().setScale(2, RoundingMode.HALF_DOWN);
    }

}
