package technology.steinhauer.demo.valtech.persistence;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import technology.steinhauer.demo.valtech.TestHelper;
import technology.steinhauer.demo.valtech.entities.Follower;
import technology.steinhauer.demo.valtech.entities.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Tests the PostManager class.
 */
public class FollowerManagerTest {

    @Before
    public void setUp() {
        TestHelper.clearFollowerTable();
    }

    @Test
    public void loadingFollowers() {
        String follower = "Alice";

        // first, we let Alice follow Bob
        String followee = "Bob";
        Follower expectedFollower = new Follower(follower, followee);
        saveFollower(expectedFollower);

        Follower actualFollower = loadFollower(follower);
        Assert.assertEquals(expectedFollower, actualFollower);
    }

    @Test
    public void saveAFollower() {
        String follower = "Bob";
        String followee = "Charlie";
        Follower expectedFollower = new Follower(follower, followee);
        FollowerManager.saveFollower(expectedFollower);

        Follower actualFollower = loadFollower(follower);
        Assert.assertEquals(expectedFollower, actualFollower);
    }

    @Test
    public void loadListOfFollowees() {
        final int expectedPostListSize = 2;
        String follower = "Bob";
        Follower firstPost =new Follower(follower, "Charlie");
        Follower secondPost = new Follower(follower, "Alice");
        saveFollower(firstPost);
        saveFollower(secondPost);

        List<Follower> followee = FollowerManager.loadFollower(follower);
        Assert.assertEquals(expectedPostListSize, followee.size());
    }

    /**
     * Gets the first follower entry returned by the database for the specified follower.
     * This method will simply fail with a runtime exception if no results where found.
     *
     * @param follower Follower, you need the first followee relationship from
     * @return The found Follower
     */
    private Follower loadFollower(String follower) {
        List<Follower> followers = FollowerManager.loadFollower(follower);
        Follower foundFollower = followers.get(0);

        return foundFollower;
    }

    /**
     * Persists the given follower relationship in the database without using any external save method.
     *
     * @param follower Follower relationship that should be persisted
     */
    private void saveFollower(Follower follower) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(follower);
        session.getTransaction().commit();
        session.close();
    }
}
