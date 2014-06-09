package technology.steinhauer.demo.valtech;

import org.hibernate.Session;
import technology.steinhauer.demo.valtech.persistence.HibernateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Small helper class for logic needed in more than one test case.
 */
public class TestHelper {

    /**
     * This method will delete ALL entries in the POSTS table. Ensure to not use productive
     * database settings (e.g. by using a hibernate.cfg.xml in src/test/resources).
     */
    public static void clearPostTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery("delete from Post").executeUpdate();
        session.close();
    }

    /**
     * This method will delete ALL entries in the FOLLOWERS table. Ensure to not use productive
     * database settings (e.g. by using a hibernate.cfg.xml in src/test/resources).
     */
    public static void clearFollowerTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery("delete from Follower").executeUpdate();
        session.close();
    }

    /**
     * Returns a Date that reflects the current moment but yesterday.
     *
     * @return Date that is today minus 1 day.
     */
    public static Date getYesterdaysDate() {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);

        return yesterday.getTime();
    }
}
