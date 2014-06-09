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
            return new UnknownCommand();
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
                    result = new UnknownCommand();
                }
            }
        } else {
            result = new TimelineCommand(firstCommand);
        }

        return result;
    }

    /**
     * A wall was requested for the given user.
     *
     * @param username The user, the wall was requested for.
     * @return Corresponding WallCommand
     */
    private static Command createWallCommand(String username) {
        return new WallCommand(username);
    }

    /**
     * The provided user will follow somebody. This method parses the rest of the
     * input to extract the followee's username and returns the corresponding
     * FollowCommand.
     *
     * @param commandScanner Scanner instance that was also used to get the username.
     * @param username The follower's username.
     * @return The corresponding FollowCommand
     */
    private static Command createFollowCommand(Scanner commandScanner, String username) {
        Command followCommand = null;

        String follow;
        if (commandScanner.hasNext()) {
            follow = commandScanner.next();
            followCommand = new FollowCommand(username, follow);
        }

        return followCommand;
    }

    /**
     * The given user will post something. This method creates the corresponding PostCommand by extracting
     * the message from the given input.
     * The extraction is done by removing the username and the command part {@link CommandParser#POST_COMMAND_STRING}
     * from the beginning of the input string (commandInput).
     *
     * @param commandInput The complete input string from the command line
     * @param username The user who wants to post
     * @return The corresponding PostCommand
     */
    private static Command createPostCommand(String commandInput, String username) {
        Command postCommand;
        final int numberOfUsedSpaces = 2;
        final int messageStartPosition = username.length() + POST_COMMAND_STRING.length() + numberOfUsedSpaces;

        String message = commandInput.substring(messageStartPosition);

        postCommand = new PostCommand(username, message);
        return postCommand;
    }


}
