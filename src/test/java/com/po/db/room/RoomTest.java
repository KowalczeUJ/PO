package com.po.db.room;

import com.po.SessionFactoryRule;
import com.po.room.RoomView;
import com.po.room.repository.RoomRepository;
import com.po.room.repository.RoomRepositoryImpl;
import org.hibernate.Session;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

public class RoomTest {

    @Rule
    public final SessionFactoryRule sessionRule = new SessionFactoryRule();

    @Test
    public void shouldPersistEntityWithFullDataSpecified() {
        Session session = sessionRule.getSession();

        Room room = Room.builder()
                .number("212A")
                .floor(4)
                .beds(6)
                .type(RoomType.DELUXE)
                .pricePerNight(new BigDecimal("150.00"))
                .build();

        session.save(room);
        sessionRule.commit();

        RoomRepository roomRepository = new RoomRepositoryImpl(sessionRule.getsessionFactory());

        List<RoomView> rooms = roomRepository.getAllRooms();

        assertNotNull(rooms);
        assertThat(rooms.size()).isEqualTo(1);
        assertThat(rooms.get(0).getBeds()).isEqualTo(room.getBeds());
        assertThat(rooms.get(0).getFloor()).isEqualTo(room.getFloor());
        assertThat(rooms.get(0).getType()).isEqualTo(room.getType());
        assertThat(rooms.get(0).getPricePerNight()).isEqualTo(room.getPricePerNight());
    }

}
