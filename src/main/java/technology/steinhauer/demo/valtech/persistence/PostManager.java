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
     * Loads all posts of give user and returns a list with the youngest post at
     * the first position.
     *
     * @param username User you want the posts from.
     * @return List of user posts, ordered by post date (youngest first)
     */
    public static List<Post> loadPosts(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Post p where p.username = :username order by p.date asc");
        query.setParameter("username", username);

        List<Post> userPosts = query.list();
        return userPosts;
    }
}
