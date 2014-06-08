package technology.steinhauer.demo.valtech;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * This collection of tests is meant to check the TimelineService is working correctly.
 */
public class TimelineTest {

    @Before
    public void resetTimelines() {
        TimelineService.reset();
    }

    @Test
    public void getUsersTimeline() {
        String username = "Alice";
        List<Post> expectedUserPosts = Collections.emptyList();
        List<Post> actualUserPosts = TimelineService.getUserTimeline(username);

        Assert.assertEquals(expectedUserPosts, actualUserPosts);
    }

    @Test
    public void addPostToTimeline() {
        String username = "Alice";
        String message = "I love the weather today";
        Date postDate = new Date();
        Post newPost = new Post(username, message, postDate);

        TimelineService.addPost(newPost);

        List<Post> userPosts = TimelineService.getUserTimeline(username);

        final int expectedEntriesInUsersTimeline = 1;
        final int actualEntriesInUsersTimeline = userPosts.size();
        Assert.assertEquals(expectedEntriesInUsersTimeline, actualEntriesInUsersTimeline);
    }

    @Test
    public void postsAreStoredProperly() {
        String username = "Bob";
        String message = "Oh, we lost!";
        Date postDate = new Date();
        Post expectedPost = new Post(username, message, postDate);

        TimelineService.addPost(expectedPost);

        List<Post> userPosts = TimelineService.getUserTimeline(username);
        Post actualPost = userPosts.get(0);

        Assert.assertEquals(expectedPost, actualPost);
    }

}
