package technology.steinhauer.demo.valtech.persistence;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import technology.steinhauer.demo.valtech.TestHelper;
import technology.steinhauer.demo.valtech.entities.Post;

import java.util.Date;
import java.util.List;

/**
 * This test has exact one use case: I needed a short spike for Hibernate and HSQLDB.
 */
public class QuickHibernateTest {

    @Test
    public void simpleHsqlDbExample() {
        TestHelper.clearPostTable();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Post newPost = new Post("Alice", "I love the weather today", new Date());
        Post secondPost = new Post("Bob", "Oh, we lost!", new Date());
        session.save(newPost);
        session.save(secondPost);

        session.getTransaction().commit();

        // load it
        session.beginTransaction();
        List result = session.createQuery("from Post").list();
        session.getTransaction().commit();
        session.close();

        System.err.println("Found #" + result.size() + " entries");
        Assert.assertEquals(2, result.size());
    }


}
