package com.po.room.repository;

import com.po.SessionFactoryRule;
import com.po.common.Period;
import com.po.db.room.Room;
import com.po.db.room.RoomType;
import com.po.room.RoomData;
import com.po.room.RoomView;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomRepositoryImplTest {

    private Room ROOM;
    private RoomRepository repository;

    @Rule
    public final SessionFactoryRule sessionRule = new SessionFactoryRule();

    @Before
    public void setup() {
        repository = new RoomRepositoryImpl(sessionRule.getsessionFactory());

        Session session = sessionRule.getSession();

        ROOM = Room.builder()
                .number("2B")
                .floor(6)
                .beds(6)
                .type(RoomType.PREMIER)
                .pricePerNight(new BigDecimal("350.00"))
                .build();

        session.save(ROOM);
        sessionRule.commit();
    }

    @Test
    public void shouldSaveRoom() {
        Room room = Room.builder()
                .number("1A")
                .floor(5)
                .beds(3)
                .type(RoomType.DELUXE)
                .pricePerNight(new BigDecimal("175.00"))
                .build();

        RoomView expected = RoomView.builder()
                .number(room.getNumber())
                .floor(room.getFloor())
                .beds(room.getBeds())
                .type(room.getType())
                .pricePerNight(room.getPricePerNight())
                .build();

        repository.save(room);
        List<RoomView> rooms = repository.getAllRooms();

        assertThat(rooms).contains(expected);
    }

    @Test
    public void shouldDeleteRoom() {
        repository.delete(ROOM.getNumber());
        List<RoomView> rooms = repository.getAllRooms();

        assertThat(rooms).isEmpty();
    }

    @Test
    public void shouldGetAvailableRooms() {
        Period period = new Period(LocalDate.of(2018, 11, 30), LocalDate.of(2018, 12, 14));

        RoomView expectedRoom = RoomView.builder()
                .number(ROOM.getNumber())
                .floor(ROOM.getFloor())
                .beds(ROOM.getFloor())
                .type(ROOM.getType())
                .pricePerNight(ROOM.getPricePerNight())
                .build();

        List<RoomView> availableRooms = repository.getAvailableRooms(period);

        assertThat(availableRooms)
                .isNotEmpty()
                .contains(expectedRoom);
    }

    @Test
    public void shouldGetRoomDataForRoomId() {
        RoomData roomData = repository.getRoomDataForId(ROOM.getId());

        assertThat(roomData.getId()).isEqualTo(ROOM.getId());
        assertThat(roomData.getType()).isEqualTo(ROOM.getType());
        assertThat(roomData.getPricePerNight()).isEqualTo(ROOM.getPricePerNight());
    }

    @Test
    public void shouldGetAllRooms() {
        List<RoomView> rooms = repository.getAllRooms();

        RoomView expectedRoom = RoomView.builder()
                .number(ROOM.getNumber())
                .floor(ROOM.getFloor())
                .beds(ROOM.getFloor())
                .type(ROOM.getType())
                .pricePerNight(ROOM.getPricePerNight())
                .build();

        assertThat(rooms)
                .isNotEmpty()
                .containsExactly(expectedRoom);
    }

}
