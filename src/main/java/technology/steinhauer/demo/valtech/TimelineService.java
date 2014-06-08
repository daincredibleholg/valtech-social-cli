package technology.steinhauer.demo.valtech;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class TimelineService {

    private static Map<String, List<Post>> timelines = new HashMap<>();

    public static List<Post> getUserTimeline(String username) {
        List<Post> userTimeline;

        if (timelines.containsKey(username)) {
            userTimeline = timelines.get(username);
        } else {
            userTimeline = new LinkedList<>();
            timelines.put(username, userTimeline);
        }

        return userTimeline;
    }

    public static void addPost(Post newPost) {
        List<Post> userTimeline = getUserTimeline(newPost.getUsername());
        userTimeline.add(newPost);
    }

    /**
     * This method is with good reason only available in package scope. It is mainly needed
     * for testing purposes and reinitiates the timelines. In other words: it throws away
     * every content, currently available on any timeline.
     */
    static void reset() {
        timelines = new HashMap<>();
    }
}
