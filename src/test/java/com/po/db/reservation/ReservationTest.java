package com.po.db.reservation;

import com.po.SessionFactoryRule;
import com.po.db.room.Room;
import com.po.db.room.RoomType;
import com.po.db.user.User;
import com.po.db.user.UserType;
import com.po.reservation.dto.ReservationDto;
import com.po.reservation.repository.ReservationRepository;
import com.po.reservation.repository.ReservationRepositoryImpl;
import org.hibernate.Session;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

public class ReservationTest {

    @Rule
    public final SessionFactoryRule sessionRule = new SessionFactoryRule();

    @Test
    public void shouldPersistEntityWithFullDataSpecified() {
        Session session = sessionRule.getSession();

        User user = User.builder()
                .id(1)
                .username("test")
                .password("test123")
                .type(UserType.BASIC)
                .isRegular(false)
                .createdOn(LocalDateTime.now())
                .build();

        session.save(user);

        Room room = Room.builder()
                .id(5)
                .number("2A")
                .beds(2)
                .floor(2)
                .pricePerNight(BigDecimal.valueOf(20.00))
                .type(RoomType.DELUXE)
                .build();

        session.save(room);

        Reservation reservation = Reservation.builder()
                .user(user)
                .room(room)
                .price(new BigDecimal("1500.00"))
                .persons(2)
                .startDate(LocalDate.of(2019, 3, 13))
                .endDate(LocalDate.of(2019, 3, 15))
                .createdOn(LocalDateTime.now())
                .build();

        session.save(reservation);
        sessionRule.commit();

        ReservationRepository repository = new ReservationRepositoryImpl(sessionRule.getsessionFactory());

        List<ReservationDto> users = repository.getAllReservations();

        assertNotNull(users);
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getUserId()).isNotNull().isPositive();
        assertThat(users.get(0).getUsername()).isEqualTo(reservation.getUser().getUsername());
        assertThat(users.get(0).getIsRegular()).isEqualTo(reservation.getUser().getIsRegular());
        assertThat(users.get(0).getRoomId()).isEqualTo(reservation.getRoom().getId());
        assertThat(users.get(0).getRoomNumber()).isEqualTo(reservation.getRoom().getNumber());
        assertThat(users.get(0).getRoomType()).isEqualTo(reservation.getRoom().getType());
        assertThat(users.get(0).getTotalPrice()).isEqualTo(reservation.getPrice());
        assertThat(users.get(0).getPersons()).isEqualTo(reservation.getPersons());
        assertThat(users.get(0).getStartDate()).isEqualTo(reservation.getStartDate());
        assertThat(users.get(0).getEndDate()).isEqualTo(reservation.getEndDate());
        assertThat(users.get(0).getCreatedOn()).isEqualTo(reservation.getCreatedOn());
    }

}
