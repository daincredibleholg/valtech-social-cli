package technology.steinhauer.demo.valtech.commands;

import java.util.Scanner;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class CommandParser {
    private static final String POST_COMMAND_STRING = "->";

    public static Command parse(String commandInput) {
        Command result;
        Scanner commandScanner = new Scanner(commandInput);

        String firstCommand;
        if (commandScanner.hasNext()) {
            firstCommand = commandScanner.next();
        } else {
            // empty line - nothing to do
            return null;
        }

        if (firstCommand.equals("quit")) {
            result = new QuitCommand();
        } else if (commandScanner.hasNext()) {
            String secondCommand = commandScanner.next();

            switch (secondCommand) {
                case POST_COMMAND_STRING: {
                    result = createPostCommand(commandScanner, commandInput, firstCommand);
                    break;
                }
                default: {
                    result = null;
                }
            }
        } else {
            result = new TimelineCommand(firstCommand);
        }

        return result;
    }

    private static Command createPostCommand(Scanner commandScanner, String commandInput, String username) {
        Command postCommand;
        final int numberOfUsedSpaces = 2;
        final int messageStartPosition = username.length() + POST_COMMAND_STRING.length() + numberOfUsedSpaces;

        String message = commandInput.substring(messageStartPosition);

        postCommand = new PostCommand(username, message);
        return postCommand;
    }


}
