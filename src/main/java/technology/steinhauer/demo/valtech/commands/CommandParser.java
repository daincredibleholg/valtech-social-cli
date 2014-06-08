package technology.steinhauer.demo.valtech.commands;

import java.util.Scanner;

/**
 * This class provides a static parsing method to create a Command instance
 * based on the given command line input.
 */
public class CommandParser {
    private static final String POST_COMMAND_STRING = "->";
    private static final String FOLLOW_COMMAND_STRING = "follows";
    private static final String WALL_COMMAND_STRING = "wall";

    /**
     * This method returns a Command based on commandInput. If no
     * known command is found, the method simply returns null.
     *
     * @param commandInput Input from command line
     * @return The corresponding Command
     */
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
                    result = createPostCommand(commandInput, firstCommand);
                    break;
                }
                case FOLLOW_COMMAND_STRING: {
                    result = createFollowCommand(commandScanner, firstCommand);
                    break;
                }
                case WALL_COMMAND_STRING: {
                    result = createWallCommand(firstCommand);
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

    private static Command createWallCommand(String username) {
        return new WallCommand(username);
    }

    private static Command createFollowCommand(Scanner commandScanner, String username) {
        Command followCommand = null;

        String follow;
        if (commandScanner.hasNext()) {
            follow = commandScanner.next();
            followCommand = new FollowCommand(username, follow);
        }

        return followCommand;
    }

    private static Command createPostCommand(String commandInput, String username) {
        Command postCommand;
        final int numberOfUsedSpaces = 2;
        final int messageStartPosition = username.length() + POST_COMMAND_STRING.length() + numberOfUsedSpaces;

        String message = commandInput.substring(messageStartPosition);

        postCommand = new PostCommand(username, message);
        return postCommand;
    }


}
