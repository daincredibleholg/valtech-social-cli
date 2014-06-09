package technology.steinhauer.demo.valtech.text_device;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Here are all (for me) possible unit test for the ConsoleTextDevice class.
 * As java.io.Console is a final class it is hard to mock. So I will just
 * test the constructor works as expected.
 * As there is no null instance for Console allowed and the class ConsoleTextDevice
 * then only works more or less like a adapter, we don't need to test quite further
 * (beside the fact that it would be better to do so).
 */
public class ConsoleTextDeviceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void consoleIsNotDirectlyTestable() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("No Console available");
        ConsoleTextDevice device = new ConsoleTextDevice();
    }

    @Test
    public void nullConsoleIsForbidden() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("No Console available");
        ConsoleTextDevice device = new ConsoleTextDevice(null);
    }

}
