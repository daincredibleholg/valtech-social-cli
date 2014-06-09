package technology.steinhauer.demo.valtech.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import technology.steinhauer.demo.valtech.entities.Follower;

import java.util.List;

/**
 * Created by hsteinhauer on 09.06.14.
 */
public class FollowerManager {
    /**
     * Returns all follower-followee relationships (i.e. you can get all user, the given
     * follower follows) for a given follower.
     *
     * @param follower The user, you want the followees for.
     * @return All follower-followee relationships found in DB
     */
    public static List<Follower> loadFollower(String follower) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Follower f where f.follower = :follower");
        query.setParameter("follower", follower);
        List<Follower> followers = query.list();
        session.close();

        return followers;
    }

    /**
     * Persists the given follower-followee relationship.
     *
     * @param follower Relationship to persist
     */
    public static void saveFollower(Follower follower) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(follower);
        session.getTransaction().commit();
    }

}
