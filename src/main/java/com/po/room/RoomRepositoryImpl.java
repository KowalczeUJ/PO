package com.po.room;

import com.po.db.room.Room;
import com.po.common.Period;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private Session session;

    public RoomRepositoryImpl(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    @Override
    public void save(Room room) {
        session.save(room);
    }

    @Override
    public void delete(String roomNumber) {
        session.createQuery("DELETE FROM Room WHERE number = :number")
                .setParameter("number", roomNumber).executeUpdate();
    }

    @Override
    public List<RoomView> getAvailableRooms(Period period) {
        Query query = session.createQuery(
                "SELECT new com.po.room.RoomView(" +
                    "   room.floor, " +
                    "   room.beds, " +
                    "   room.type, " +
                    "   room.pricePerNight" +
                    ") " +
                    "FROM Room room " +
                    "WHERE room.id not in (" +
                    "   SELECT r.room.id " +
                    "   FROM Reservation r " +
                    "   WHERE :start BETWEEN r.startDate AND r.endDate " +
                    "   OR :end BETWEEN r.startDate AND r.endDate" +
                    ")",
                RoomView.class
        );
        query.setParameter("start", period.getStartDate());
        query.setParameter("end", period.getEndDate());

        return query.getResultList();
    }

}
