package technology.steinhauer.demo.valtech.text_device;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by hsteinhauer on 07.06.14.
 */
public class TextDeviceFactory {

    private static TextDevice device;

    /**
     * This class should not be instantiated...
     */
    private TextDeviceFactory() {  }

    public static TextDevice createTextDevice() throws Exception {
        // once the device is known, we can return it directly
        if (device != null) {
            return device;
        }

        Console console = System.console();

        if (console == null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(System.out);

            device = new CharacterTextDevice(reader, writer);
        } else {
            device = new ConsoleTextDevice(console);
        }
        return device;
    }
}
