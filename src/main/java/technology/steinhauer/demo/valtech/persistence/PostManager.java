package technology.steinhauer.demo.valtech.persistence;

import org.hibernate.Query;
import org.hibernate.Session;
import technology.steinhauer.demo.valtech.entities.Post;

import java.util.List;

/**
 * This class saves and loads posts.
 */
public class PostManager {

    /**
     * Persists the given post in the DB.
     *
     * @param post Post to persist
     */
    public static void savePost(Post post) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(post);
        session.getTransaction().commit();

        session.close();
    }

    /**
     * Loads all posts of given user and returns descending ordered list (i.e. the youngest post is the first
     * element).
     *
     * @param username User you want the posts from.
     * @return List of user posts
     */
    public static List<Post> loadPosts(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Post p where p.username = :username order by p.date desc");
        query.setParameter("username", username);

        List<Post> userPosts = query.list();
        return userPosts;
    }

    /**
     * Loads all posts done by the given user or by users, the user is following. The list of posts is
     * ordered descending, i.e. the youngest post is the first element.
     *
     * @param username User, you want all posts for his wall for
     * @return The found posts
     */
    public static List<Post> loadWallPosts(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String followerSubquery = "select followee from Follower where follower = :username";
        String completeQuery = "from Post p where p.username = :username or p.username in (" + followerSubquery + ") order by p.date desc";

        Query query = session.createQuery(completeQuery);
        query.setParameter("username", username);

        List<Post> wallPosts = query.list();

        return wallPosts;
    }
}
