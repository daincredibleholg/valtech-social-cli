package technology.steinhauer.demo.valtech.persistence;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import technology.steinhauer.demo.valtech.TestHelper;
import technology.steinhauer.demo.valtech.entities.Follower;
import technology.steinhauer.demo.valtech.entities.Post;

import java.util.Date;
import java.util.List;

/**
 * Tests the PostManager class.
 */
public class PostManagerTest {

    @Before
    public void setUp() {
        TestHelper.clearPostTable();
    }

    @Test
    public void loadingAPost() {
        String username = "Alice";
        // first, we save one post
        Post expectedPost = new Post(username, "I love the weather today", new Date());
        savePost(expectedPost);

        Post actualPost = loadPost(username);
        Assert.assertEquals(expectedPost, actualPost);
    }

    @Test
    public void saveAPost() {
        String username = "Bob";
        Post expectedPost = new Post(username, "Oh, we lost!", new Date());
        PostManager.savePost(expectedPost);

        Post actualPost = loadPost(username);
        Assert.assertEquals(expectedPost, actualPost);
    }

    @Test
    public void loadListOfPosts() {
        final int expectedPostListSize = 2;
        String username = "Bob";
        Post firstPost = new Post(username, "Oh, we lost!", TestHelper.getYesterdaysDate());
        Post secondPost = new Post(username, "at least it's sunny", new Date());
        savePost(firstPost);
        savePost(secondPost);

        List<Post> posts = PostManager.loadPosts(username);
        Assert.assertEquals(expectedPostListSize, posts.size());
    }

    @Test
    public void loadWallPostsWithoutFollowing() {
        TestHelper.clearFollowerTable();

        String username = "Bob";
        // assume the first post were made yesterday...
        Post firstPost = new Post(username, "Oh, we lost!", TestHelper.getYesterdaysDate());

        // ... and the second post is just from now
        Post secondPost = new Post(username, "at least it's sunny", new Date());
        savePost(firstPost);
        savePost(secondPost);

        List<Post> posts = PostManager.loadWallPosts(username);

        Assert.assertEquals(secondPost, posts.get(0));
        Assert.assertEquals(firstPost, posts.get(1));
    }

    @Test
    public void loadWallPostsWithFollowing() {
        TestHelper.clearFollowerTable();

        String username = "Bob";
        String followee = "Alice";

        // assume, Bob follows Alice
        Follower follower = new Follower(username, followee);
        FollowerManager.saveFollower(follower);

        // also assume, Alice posted something yesterday
        Post alicePost = new Post(followee, "I love the weather today", TestHelper.getYesterdaysDate());
        savePost(alicePost);

        // Bob posts something quite now
        Post bobsPost = new Post(username, "Oh, we lost!", new Date());
        savePost(bobsPost);

        // now, check if we get both posts, in the correct order, on Bobs wall
        List<Post> bobsWallPosts = PostManager.loadWallPosts(username);

        Assert.assertEquals(bobsPost, bobsWallPosts.get(0));
        Assert.assertEquals(alicePost, bobsWallPosts.get(1));
    }

    /**
     * Gets the first post returned by the database for the specified username.
     * This method will simply fail with a runtime exception if no results where found.
     *
     * @param username User, you need the first post from DB results from
     * @return The found post
     */
    private Post loadPost(String username) {
        List<Post> posts = PostManager.loadPosts(username);
        Post foundPost = posts.get(0);

        return foundPost;
    }

    /**
     * Persists the given post in the database without using any external save method.
     *
     * @param post Post that should be persisted
     */
    private void savePost(Post post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(post);
        session.getTransaction().commit();
        session.close();
    }
}
