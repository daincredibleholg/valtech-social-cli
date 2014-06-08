package technology.steinhauer.demo.valtech.text_device;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for static factory method to create a appropriate TextDevice instance.
 * Again, the static nature of System.console() and Console stopped me from
 * testing the favtory more effective.
 */
public class TextDeviceFactoryTest {

    @Test
    public void createTextDevice() throws Exception {
        TextDevice textDevice = TextDeviceFactory.createTextDevice();
        Assert.assertNotNull(textDevice);
    }

    @Test
    public void characterTextDeviceShouldBeDefaultInTests() throws Exception {
        TextDevice textDevice = TextDeviceFactory.createTextDevice();

        Assert.assertTrue(textDevice instanceof CharacterTextDevice);
    }

    @Test
    public void checkInstanceIsOnlyCreatedOnce () throws Exception {
        TextDevice textDevice1 = TextDeviceFactory.createTextDevice();
        TextDevice textDevice2 = TextDeviceFactory.createTextDevice();

        Assert.assertTrue(textDevice1 == textDevice2);
    }
}
