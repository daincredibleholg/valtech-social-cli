package technology.steinhauer.demo.valtech;

import technology.steinhauer.demo.valtech.commands.Command;
import technology.steinhauer.demo.valtech.commands.CommandParser;
import technology.steinhauer.demo.valtech.text_device.TextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDeviceFactory;

import java.io.IOException;

/**
 * The main class, the start of everything...
 */
public class SocialShell {

    private static final String PROMPT = "> ";

    public static void main(String... args) {
        SocialShell shell = new SocialShell();
        shell.start();
    }

    private void start() {
        try {
            TextDevice device = TextDeviceFactory.createTextDevice();
            startCommandLoop(device);
        } catch (Exception e) {
            System.err.println("Following error occurred: [" + e.getMessage() + "]");
            System.exit(1);
        }
    }

    private void startCommandLoop(final TextDevice device) throws IOException {
        while (true) {
            String incomingCommandString = device.readLine(PROMPT);
            Command command = CommandParser.parse(incomingCommandString);
            command.execute();
        }
    }

}
