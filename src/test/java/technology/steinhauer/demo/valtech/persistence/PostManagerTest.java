package technology.steinhauer.demo.valtech.persistence;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import technology.steinhauer.demo.valtech.Post;
import technology.steinhauer.demo.valtech.TestHelper;

import java.util.Date;
import java.util.List;

/**
 * Created by hsteinhauer on 09.06.14.
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
        Post firstPost =new Post(username, "Oh, we lost!", new Date());
        Post secondPost = new Post(username, "at least it's sunny", new Date());
        savePost(firstPost);
        savePost(secondPost);

        List<Post> posts = PostManager.loadPosts(username);
        Assert.assertEquals(expectedPostListSize, posts.size());
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
