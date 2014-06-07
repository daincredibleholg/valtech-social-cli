package technology.steinhauer.demo.valtech;

import technology.steinhauer.demo.valtech.text_device.TextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDeviceFactory;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by hsteinhauer on 06.06.14.
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
            System.err.println("Following error occured: [" + e.getMessage() + "]");
        }
    }

    private void startCommandLoop(final TextDevice device) throws IOException {
        while (true) {
            String command = device.readLine(PROMPT);

            commandInterpreter(command, device);
        }
    }

    private void commandInterpreter(String command, TextDevice device) {
        Scanner scanner = new Scanner(command);

        if (scanner.hasNext()) {
            String userOrQuitCommand = scanner.next();
            quitIfRequested(userOrQuitCommand);

            if (!scanner.hasNext()) {
                //TODO
                device.writer().println(userOrQuitCommand + " timelime requested");
            } else {
                String option = scanner.next();

                String restOfCommand = "";
                if (scanner.hasNext()) {
                     restOfCommand = command.substring(userOrQuitCommand.length() + option.length() + 2);
                }

                switch (option) {
                    case "->": {
                        System.out.println("Posting, content: [" + restOfCommand + "]");
                        break;
                    }
                    case "follows": {
                        System.out.println(userOrQuitCommand + " follows " + restOfCommand + "now");
                        break;
                    }
                    case "wall": {
                        System.out.println(userOrQuitCommand + "'s wall requested");
                        break;
                    }
                }
            }
        }
    }

    private void quitIfRequested(String command) {
        if (command.toLowerCase().equals("quit")) {
            System.exit(0);
        }
    }

}
