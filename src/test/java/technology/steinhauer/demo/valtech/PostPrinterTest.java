package technology.steinhauer.demo.valtech;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import technology.steinhauer.demo.valtech.text_device.CharacterTextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDevice;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

/**
 * Tests the PostPrinter utility
 */
public class PostPrinterTest {

    private StringWriter stringWriter;


    /**
     * Resets the PostPrinter and TimelineService before each test
     */
    @Before
    public void setUp() {
        PostPrinter.setTextDevice(null);
        TimelineService.reset();
    }

    @Test
    public void testTimelineOutput() throws Exception {
        // Prepare text device for test
        TextDevice device = getTextDeviceForTest();
        PostPrinter.setTextDevice(device);

        // Prepare post and "post it"
        String username = "Bob";
        String message = "Oh, we lost!";
        Date postDate = new Date();

        Post post = new Post(username, message, postDate);
        TimelineService.addPost(post);

        PostPrinter.printTimeline(username);

        String expectedValue = message + " (1 second ago)\n";
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
        // Prepare text device for test
        TextDevice device = getTextDeviceForTest();
        PostPrinter.setTextDevice(device);

        // Prepare posts and "post them"
        String username = "Bob";
        String firstMessage = "Oh, we lost!";
        Date firstPostDate = new Date();
        Post post = new Post(username, firstMessage, firstPostDate);
        TimelineService.addPost(post);

        String secondMessage = "at least it's sunny";
        Date secondPostDate = new Date();
        post = new Post(username, secondMessage, secondPostDate);
        TimelineService.addPost(post);

        PostPrinter.printTimeline(username);

        String expectedValue = secondMessage + " (1 second ago)\n";
        expectedValue += firstMessage + " (1 second ago)\n";
        String actualValue = stringWriter.getBuffer().toString();
        Assert.assertEquals(expectedValue, actualValue);

    }

    private TextDevice getTextDeviceForTest() {
        StringReader stringReader = new StringReader("demo");
        BufferedReader reader = new BufferedReader(stringReader);
        stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        return new CharacterTextDevice(reader, writer);
    }
}