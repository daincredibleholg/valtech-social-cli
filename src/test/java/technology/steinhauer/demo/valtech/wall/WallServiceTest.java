package technology.steinhauer.demo.valtech.wall;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import technology.steinhauer.demo.valtech.entities.Post;

import java.util.Date;
import java.util.List;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class WallServiceTest {

    @Before
    public void setUp () {
        WallService.reset();
    }

    @Test
    public void addPost() {
        String username = "Charlie";
        String message = "I'm in New York today! Anyone wants to have a coffee?";
        Date postDate = new Date();
        Post expectedPost = new Post(username, message, postDate);

        WallService.addPost(expectedPost);

        List<Post> userWallEntries = WallService.getPosts(username);
        Post actualPost = userWallEntries.get(0);

        Assert.assertEquals(expectedPost, actualPost);
    }

    @Test
    public void followUser() {
        String followee = "Alice";
        String follower = "Charlie";

        WallService.addFollower(followee, follower);

//        String message =
//        WallService.addPost();
    }
}
