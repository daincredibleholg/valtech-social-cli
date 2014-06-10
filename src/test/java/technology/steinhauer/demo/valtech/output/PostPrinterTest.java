package technology.steinhauer.demo.valtech.output;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millisecond;
import technology.steinhauer.demo.valtech.TestHelper;
import technology.steinhauer.demo.valtech.entities.Post;
import technology.steinhauer.demo.valtech.persistence.PostManager;
import technology.steinhauer.demo.valtech.text_device.CharacterTextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDevice;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Locale;

/**
 * Tests the PostPrinter utility
 */
public class PostPrinterTest {

    private StringWriter stringWriter;

    /**
     * Resets the PostPrinter text device and the Post DB table before each test
     */
    @Before
    public void setUp() {
        PostPrinter.setTextDevice(null);
        TestHelper.clearPostTable();
    }

    @Test
    public void testTimelineOutput() throws Exception {
        preparePostPrinter();

        // Prepare post and "post it"
        String username = "Bob";
        String message = "Oh, we lost!";
        Date postDate = new Date();

        Post post = new Post(username, message, postDate);
        PostManager.savePost(post);

        PostPrinter.printTimeline(username);

        PrettyTime prettyTime = getPrettyTimeInstance();

        String expectedValue = message + " (" + prettyTime.format(postDate) + ")" + System.lineSeparator();
        String actualValue = stringWriter.getBuffer().toString();
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void textDeviceLookupCheck() {
        TextDevice actualDevice = PostPrinter.getTextDevice();

        Assert.assertNotNull(actualDevice);
    }

    @Test
    public void postsAreShownInReverseOrder() {
        preparePostPrinter();

        // Prepare posts and "post them"
        String username = "Bob";
        String firstMessage = "Oh, we lost!";
        Date firstPostDate = new Date();
        Post post = new Post(username, firstMessage, firstPostDate);
        PostManager.savePost(post);

        String secondMessage = "at least it's sunny";
        Date secondPostDate = new Date();
        post = new Post(username, secondMessage, secondPostDate);
        PostManager.savePost(post);

        PostPrinter.printTimeline(username);

        PrettyTime prettyTime = getPrettyTimeInstance();

        String expectedValue = secondMessage + " (" + prettyTime.format(secondPostDate) + ")" + System.lineSeparator();
        expectedValue += firstMessage + " (" + prettyTime.format(firstPostDate) + ")" + System.lineSeparator();
        String actualValue = stringWriter.getBuffer().toString();
        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void wallWithoutFollowingShowsPersonalPostsOnly() {
        preparePostPrinter();

        // we create and save two posts first
        String username = "Bob";
        Post firstPost = new Post(username, "Oh, we lost!", TestHelper.getYesterdaysDate());
        Post secondPost = new Post(username, "at least it's sunny", new Date());
        PostManager.savePost(firstPost);
        PostManager.savePost(secondPost);

        PostPrinter.printWall(username);

        PrettyTime prettyTime = getPrettyTimeInstance();
        String expectedValue = username + " - " + secondPost.getMessage() + " (" +
                prettyTime.format(secondPost.getDate()) + ")" + System.lineSeparator();
        expectedValue += username + " - " + firstPost.getMessage() + " (" +
                prettyTime.format(firstPost.getDate()) + ")" + System.lineSeparator();

        String actualValue = stringWriter.getBuffer().toString();

        Assert.assertEquals(expectedValue, actualValue);

    }

    /**
     * Initialises a testable, valid text device and set PostPrinter up to use it.
     */
    private void preparePostPrinter() {
        TextDevice device = getTextDeviceForTest();
        PostPrinter.setTextDevice(device);
    }

    /**
     * Initialises and returns a CharacterTextDevice.
     * Use the stringWriter member variable to get output for test.
     *
     * @return Configured CharacterTextDevice
     */
    private TextDevice getTextDeviceForTest() {
        StringReader stringReader = new StringReader("demo");
        BufferedReader reader = new BufferedReader(stringReader);
        stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        return new CharacterTextDevice(reader, writer);
    }

    /**
     * Returns a PrettyTime instance without the time units "JustNow" and "Milliseconds", so the smallest
     * unit, used for "xy UNIT ago" is UNIT = second.
     *
     * @return Preconfigured PrettyTime instance
     */
    private PrettyTime getPrettyTimeInstance() {
        PrettyTime prettyTime = new PrettyTime();

        prettyTime.removeUnit(JustNow.class);
        prettyTime.removeUnit(Millisecond.class);
        return prettyTime;
    }
}
