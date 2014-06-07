package technology.steinhauer.demo.valtech.text_device;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Unit tests needed for CharacterTextDevice implementation.
 */
public class CharacterTextDeviceTest {

    private final static String STRING_READER_VALUE = "Expected Reader";

    private BufferedReader expectedReaderInstance;
    private StringWriter stringWriter;
    private PrintWriter expectedWriterInstance;
    private CharacterTextDevice device;

    @Before
    public void initCharacterDevice () {
        StringReader stringReader = new StringReader(STRING_READER_VALUE);
        expectedReaderInstance = new BufferedReader(stringReader);

        stringWriter = new StringWriter();
        expectedWriterInstance = new PrintWriter(stringWriter);

        device = new CharacterTextDevice(expectedReaderInstance, expectedWriterInstance);
    }

    @Test
    public void deviceConstructorTestForReader () {
        Assert.assertEquals(expectedReaderInstance, device.reader());
    }

    @Test
    public void deviceConstructorTestForWriter () {
        Assert.assertEquals(expectedWriterInstance, device.writer());
    }

    @Test
    public void formatAString() {
        String format = "%05d";
        int numberToFormat = 42;
        String expectedOutput = "00042";

        device.printf(format, numberToFormat);

        Assert.assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void readALine () throws IOException {
        String actualValue = device.readLine();
        Assert.assertEquals(STRING_READER_VALUE, actualValue);
    }

    @Test
    public void readALineWithPrompt () throws IOException {
        device.readLine("> ");
        String actualValue = stringWriter.getBuffer().toString();
        Assert.assertEquals("> ", actualValue);
    }

}
