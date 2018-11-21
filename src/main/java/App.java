import com.po.db.User;
import com.po.user.UserType;
import com.po.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = User.builder()
                .username("marecki123")
                .password("qwerty")
                .type(UserType.BASIC)
                .isRegular(false)
                .build();
        session.save(user);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }

}
