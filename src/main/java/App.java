import com.po.common.Period;
import com.po.hotel.Hotel;
import com.po.hotel.HotelService;
import com.po.room.AvailableRooms;
import com.po.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.logging.Level;

import static java.util.logging.Logger.getLogger;

public class App {

    public static void main(String[] args) {
        getLogger("org.hibernate").setLevel(Level.OFF);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Hotel hotel = new HotelService(sessionFactory);

        AvailableRooms rooms = hotel.getAvailableRooms(
                new Period(
                        LocalDate.of(2018, 11, 27),
                        LocalDate.of(2018, 11, 28)
                )
        );

        System.out.println(rooms);

        sessionFactory.close();
    }

}
