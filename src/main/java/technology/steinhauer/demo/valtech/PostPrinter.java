package technology.steinhauer.demo.valtech;

import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millisecond;
import technology.steinhauer.demo.valtech.text_device.TextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDeviceFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by hsteinhauer on 08.06.14.
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

        List<Post> userTimeline = TimelineService.getUserTimeline(username);

        // we want the posts in descending order, so we simply reverse it
        Collections.reverse(userTimeline);

        for (Post post : userTimeline) {
            getTextDevice().printf(format, post.getMessage(), prettyTime.format(post.getDate()));
        }
    }

    private static PrettyTime getPrettyTimeInstance() {
        PrettyTime prettyTime = new PrettyTime();
        prettyTime.removeUnit(Millisecond.class);
        prettyTime.removeUnit(JustNow.class);
        return prettyTime;
    }
}
