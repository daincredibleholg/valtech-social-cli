package technology.steinhauer.demo.valtech;

import org.hibernate.Session;
import technology.steinhauer.demo.valtech.persistence.HibernateUtil;

/**
 * Created by hsteinhauer on 09.06.14.
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

    public static void clearFollowerTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.createQuery("delete from Follower").executeUpdate();
        session.close();
    }
}
