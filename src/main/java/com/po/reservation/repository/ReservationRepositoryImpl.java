package com.po.reservation.repository;

import com.po.db.reservation.Reservation;
import com.po.reservation.dto.ReservationDto;
import com.po.reservation.dto.UserReservationDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Session session;

    public ReservationRepositoryImpl(SessionFactory sessionFactory) {
        this.session = sessionFactory.openSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ReservationDto> getAllReservations() {
        Query query = session.createQuery(
                "SELECT new com.po.reservation.dto.ReservationDto(" +
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
    @SuppressWarnings("unchecked")
    public List<UserReservationDto> getUserReservations(int userId) {
        Query query = session.createQuery(
                "SELECT new com.po.reservation.dto.UserReservationDto(" +
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
                UserReservationDto.class
        );
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public void makeReservation(Reservation reservation) {
        session.beginTransaction();
        session.save(reservation);
        session.getTransaction().commit();
    }

    @Override
    public void deleteReservation(int reservationId) {
        session.beginTransaction();
        session.createQuery("DELETE FROM Reservation WHERE id = :reservationId")
                .setParameter("reservationId", reservationId).executeUpdate();
        session.getTransaction().commit();
    }

}
