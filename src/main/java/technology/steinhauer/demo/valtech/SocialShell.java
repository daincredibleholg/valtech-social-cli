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

    private static final String COMMAND_POSTING = "->";
    private static final String COMMAND_FOLLOWS = "follows";
    private static final String COMMAND_SHOW_WALL = "wall";

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
                device.writer().println(userOrQuitCommand + " timeline requested");
            } else {
                String option = scanner.next();

                switch (option) {
                    case COMMAND_POSTING: {
                        String message = getPostMessage(scanner, command, userOrQuitCommand);
                        System.out.println("Posting, content: [" + message+ "]");
                        break;
                    }
                    case COMMAND_FOLLOWS: {
                        String follow = getFollowUserName(scanner);
                        System.out.println(userOrQuitCommand + " follows " + follow + " now");
                        break;
                    }
                    case COMMAND_SHOW_WALL: {
                        System.out.println(userOrQuitCommand + "'s wall requested");
                        break;
                    }
                }
            }
        }
    }

    private String getPostMessage(Scanner scanner, String command, String username) {
        final int numberOfUsedSeparators = 2;
        String message = "";
        if (scanner.hasNext()) {
            message = command.substring(username.length() + COMMAND_POSTING.length() + numberOfUsedSeparators);
        }
        return message;
    }

    private String getFollowUserName(Scanner scanner) {
        String follow = "";
        if (scanner.hasNext()) {
            follow = scanner.next();
        }

        return follow;
    }

    private void quitIfRequested(String command) {
        if (command.toLowerCase().equals("quit")) {
            System.exit(0);
        }
    }

}
