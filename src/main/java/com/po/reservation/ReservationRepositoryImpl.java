package com.po.reservation;

import com.po.db.reservation.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {

    private Session session;

    public ReservationRepositoryImpl(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        Query query = session.createQuery(
                "SELECT new com.po.reservation.ReservationDto(" +
                    "   user.id, " +
                    "   user.username, " +
                    "   user.isRegular, " +
                    "   room.id, " +
                    "   room.number, " +
                    "   room.type, " +
                    "   res.price, " +
                    "   res.persons, " +
                    "   res.startDate, " +
                    "   res.endDate, " +
                    "   res.createdOn " +
                    ") " +
                    "FROM Reservation res " +
                    "JOIN res.room room " +
                    "JOIN res.user user " +
                    "ORDER BY res.createdOn DESC ",
                ReservationDto.class
        );
        return query.getResultList();
    }

    @Override
    public List<UserReservationDto> getUserReservations(int userId) {
        Query query = session.createQuery(
                "SELECT new com.po.reservation.UserReservationDto(" +
                        "   user.id, " +
                        "   room.number, " +
                        "   room.type, " +
                        "   res.price, " +
                        "   res.persons, " +
                        "   res.startDate, " +
                        "   res.endDate, " +
                        "   res.createdOn " +
                        ") " +
                        "FROM Reservation res " +
                        "JOIN res.room room " +
                        "JOIN res.user user " +
                        "WHERE res.user.id = :userId " +
                        "ORDER BY res.createdOn DESC ",
                ReservationDto.class
        );
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public void makeReservation(Reservation reservation) {
        session.save(reservation);
    }

    @Override
    public void deleteReservation(int reservationId) {
        session.createQuery("DELETE FROM Reservation WHERE id = :reservationId")
                .setParameter("reservationId", reservationId);
    }

}
