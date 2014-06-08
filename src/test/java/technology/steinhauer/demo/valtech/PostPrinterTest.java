package technology.steinhauer.demo.valtech;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import technology.steinhauer.demo.valtech.text_device.CharacterTextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDeviceFactory;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class PostPrinterTest {

    /**
     * Resets the PostPrinter before each test
     */
    @Before
    public void setUp () {
        PostPrinter.setTextDevice(null);
    }

    @Test
    public void testTimelineOutput() throws Exception {
        // Prepare text device for test
        StringReader stringReader = new StringReader("demo");
        BufferedReader reader = new BufferedReader(stringReader);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        CharacterTextDevice device = new CharacterTextDevice(reader, writer);
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
}
