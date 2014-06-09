package technology.steinhauer.demo.valtech.output;

import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millisecond;
import technology.steinhauer.demo.valtech.entities.Post;
import technology.steinhauer.demo.valtech.persistence.PostManager;
import technology.steinhauer.demo.valtech.text_device.TextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDeviceFactory;

import java.util.List;

/**
 * This class holds the logic to print posts either in the context of a timeline or a wall.
 */
public class PostPrinter {
    private static TextDevice textDevice;

    public static void setTextDevice(TextDevice textDevice) {
        PostPrinter.textDevice = textDevice;
    }

    /**
     * Returns the set text device instance or, if no instance
     * is available yet, looks up for the TextDevice, the application
     * is currently using, and returns it-
     *
     * @return The TextDevice instance to interact with user.
     */
    public static TextDevice getTextDevice() {
        if (textDevice == null) {
            initTextDevice();
        }

        return textDevice;
    }

    /**
     * Tries to lookup the TextDevice, used for this application instance.
     * If anything goes wrong, it prints an error message to STDERR and
     * quits the applicaton with exit code 1.
     */
    private static void initTextDevice() {
        try {
            textDevice = TextDeviceFactory.createTextDevice();
        } catch (Exception e) {
            System.err.println("Unable to lookup workable text device for PostPrinter.");
            System.exit(1);
        }
    }

    /**
     * Prints all posts, made by the user with the given username, to the
     * text device.
     *
     * The output will look like this:
     *      This is my post (2 minutes ago)
     *
     * @param username User, you want the timeline for.
     */
    public static void printTimeline(String username) {
        final String format = "%s (%s)%n";
        final PrettyTime prettyTime = getPrettyTimeInstance();

        List<Post> userTimeline = PostManager.loadPosts(username);

        for (Post post : userTimeline) {
            getTextDevice().printf(format, post.getMessage(), prettyTime.format(post.getDate()));
        }
    }

    /**
     * Prints all posts from the given user and the users, the given user
     * is following at the moment, to the current text device.
     *
     * The output will loke like this:
     *      DemoUser - This is my post (2 minutes ago)
     *      InterestingPoster - Really interesting post (1 hour ago)
     *
     * @param username The user, you want the wall posts for.
     */
    public static void printWall(String username) {
        final String format = "%s - %s (%s)%n";
        final PrettyTime prettyTime = getPrettyTimeInstance();

        List<Post> userWall = PostManager.loadWallPosts(username);

        for (Post post : userWall) {
            getTextDevice().printf(format, post.getUsername(), post.getMessage(), prettyTime.format(post.getDate()));
        }
    }

    /**
     * Returns a pre-configured PrettyTime instance where the smallest unit is "second" (i.e. "1 second ago" is
     * the smallest ago info available.)
     *
     * @return Pre-configured PrettyTime instance.
     */
    private static PrettyTime getPrettyTimeInstance() {
        PrettyTime prettyTime = new PrettyTime();
        prettyTime.removeUnit(Millisecond.class);
        prettyTime.removeUnit(JustNow.class);
        return prettyTime;
    }
}
