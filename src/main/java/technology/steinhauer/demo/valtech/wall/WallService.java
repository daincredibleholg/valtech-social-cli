package technology.steinhauer.demo.valtech.wall;

import technology.steinhauer.demo.valtech.Post;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class WallService {
    private static Map<String, List<Post>> walls = new HashMap<>();

    public static void addPost(Post expectedPost) {
        List<Post> userWall = getPosts(expectedPost.getUsername());
        userWall.add(expectedPost);
    }

    public static List<Post> getPosts(String username) {
        if (!walls.containsKey(username)) {
            walls.put(username, new LinkedList<Post>());
        }

        return walls.get(username);
    }

    static void reset() {
        walls = new HashMap<>();
    }

    public static void addFollower(String followee, String follower) {

    }
}
