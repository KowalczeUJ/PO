package com.po.reservation.repository;

import com.po.SessionFactoryRule;
import com.po.db.reservation.Reservation;
import com.po.db.room.Room;
import com.po.db.room.RoomType;
import com.po.db.user.User;
import com.po.db.user.UserType;
import com.po.reservation.dto.ReservationDto;
import com.po.reservation.dto.UserReservationDto;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationRepositoryImplTest {

    private User USER;
    private Room ROOM;
    private Reservation RESERVATION;
    private ReservationRepository repository;

    @Rule
    public final SessionFactoryRule sessionRule = new SessionFactoryRule();

    @Before
    public void setup() {
        repository = new ReservationRepositoryImpl(sessionRule.getsessionFactory());
        Session session = sessionRule.getSession();

        USER = User.builder()
                .id(1)
                .username("test")
                .password("test123")
                .type(UserType.BASIC)
                .isRegular(false)
                .createdOn(LocalDateTime.now())
                .build();

        session.save(USER);

        ROOM = Room.builder()
                .id(5)
                .number("2A")
                .beds(2)
                .floor(2)
                .pricePerNight(BigDecimal.valueOf(20.00))
                .type(RoomType.DELUXE)
                .build();

        session.save(ROOM);

        RESERVATION = Reservation.builder()
                .user(USER)
                .room(ROOM)
                .price(new BigDecimal("1500.00"))
                .persons(2)
                .startDate(LocalDate.of(2019, 3, 13))
                .endDate(LocalDate.of(2019, 3, 15))
                .createdOn(LocalDateTime.now())
                .build();

        session.save(RESERVATION);
        sessionRule.commit();
    }

    @Test
    public void shouldGetAllReservations() {
        ReservationDto expected = ReservationDto.builder()
                .userId(USER.getId())
                .username(USER.getUsername())
                .isRegular(USER.getIsRegular())
                .roomId(ROOM.getId())
                .roomNumber(ROOM.getNumber())
                .roomType(ROOM.getType())
                .totalPrice(RESERVATION.getPrice())
                .persons(RESERVATION.getPersons())
                .startDate(RESERVATION.getStartDate())
                .endDate(RESERVATION.getEndDate())
                .createdOn(RESERVATION.getCreatedOn())
                .build();

        List<ReservationDto> reservations = repository.getAllReservations();

        assertThat(reservations)
                .hasSize(1)
                .containsExactly(expected);
    }

    @Test
    public void shouldGetUserReservation() {
        int userId = USER.getId();

        UserReservationDto expected = UserReservationDto.builder()
                .userId(USER.getId())
                .roomNumber(ROOM.getNumber())
                .roomType(ROOM.getType())
                .totalPrice(RESERVATION.getPrice())
                .persons(RESERVATION.getPersons())
                .startDate(RESERVATION.getStartDate())
                .endDate(RESERVATION.getEndDate())
                .createdOn(RESERVATION.getCreatedOn())
                .build();

        List<UserReservationDto> userReservations = repository.getUserReservations(userId);

        assertThat(userReservations)
                .hasSize(1)
                .containsExactly(expected);
    }

    @Test
    public void shouldMakeAReservation() {
        Reservation reservation = Reservation.builder()
                .user(USER)
                .room(ROOM)
                .price(new BigDecimal("1500.00"))
                .persons(2)
                .startDate(LocalDate.of(2019, 4, 25))
                .endDate(LocalDate.of(2019, 4, 29))
                .build();

        repository.makeReservation(reservation);

        List<ReservationDto> reservations = repository.getAllReservations();

        assertThat(reservations)
                .hasSize(2);
    }

    @Test
    public void shouldDeleteReservation() {
        repository.deleteReservation(RESERVATION.getId());
        List<ReservationDto> reservations = repository.getAllReservations();

        assertThat(reservations)
                .isEmpty();
    }

}