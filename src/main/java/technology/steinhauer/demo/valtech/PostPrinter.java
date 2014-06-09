package technology.steinhauer.demo.valtech;

import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millisecond;
import technology.steinhauer.demo.valtech.entities.Post;
import technology.steinhauer.demo.valtech.persistence.PostManager;
import technology.steinhauer.demo.valtech.text_device.TextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDeviceFactory;

import java.util.Collections;
import java.util.List;

/**
 * This class holds the logic to print posts either in the context of a timeline or a wall.
 */
public class PostPrinter {
    private static TextDevice textDevice;

    public static void setTextDevice(TextDevice textDevice) {
        PostPrinter.textDevice = textDevice;
    }

    public static TextDevice getTextDevice() {
        if (textDevice == null) {
            initTextDevice();
        }

        return textDevice;
    }

    private static void initTextDevice() {
        try {
            textDevice = TextDeviceFactory.createTextDevice();
        } catch (Exception e) {
            System.err.println("Unable to lookup workable text device for PostPrinter.");
        }
    }

    public static void printTimeline(String username) {
        final String format = "%s (%s)%n";
        final PrettyTime prettyTime = getPrettyTimeInstance();

        List<Post> userTimeline = PostManager.loadPosts(username);

        for (Post post : userTimeline) {
            getTextDevice().printf(format, post.getMessage(), prettyTime.format(post.getDate()));
        }
    }

    public static void printWall(String username) {
        final String format = "%s - %s (%s)%n";
        final PrettyTime prettyTime = getPrettyTimeInstance();

        List<Post> userWall = PostManager.loadWallPosts(username);

        for (Post post : userWall) {
            getTextDevice().printf(format, post.getUsername(), post.getMessage(), prettyTime.format(post.getDate()));
        }
    }

    /**
     * Returns a preconfigured PrettyTime instance where the smallest unit is "second" (i.e. "1 second ago" is
     * the smallest ago info available.)
     *
     * @return Preconfigured PrettyTime instance.
     */
    private static PrettyTime getPrettyTimeInstance() {
        PrettyTime prettyTime = new PrettyTime();
        prettyTime.removeUnit(Millisecond.class);
        prettyTime.removeUnit(JustNow.class);
        return prettyTime;
    }
}
