package technology.steinhauer.demo.valtech.commands;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the functions of CommandParser class.
 */
public class CommandParserTest {

    @Test
    public void showUserTimeline() {
        String expectedUsername = "username";
        Command actual = CommandParser.parse(expectedUsername);

        String actualUsername = actual.getUsername();
        Assert.assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void postMessage() {
        String username = "username";
        String message = "demo";
        String command = createCommandString(username, CommandParser.POST_COMMAND_STRING, message);

        Command actualCommand = CommandParser.parse(command);

        Assert.assertEquals(username, actualCommand.getUsername());
        Assert.assertEquals(message, ((PostCommand) actualCommand).getMessage());
    }

    @Test
    public void followUser() {
        String username = "Charlie";
        String follow = "Alice";
        String command = createCommandString(username, CommandParser.FOLLOW_COMMAND_STRING, follow);
        Command expectedCommand = new FollowCommand(username, follow);

        Command actualCommand = CommandParser.parse(command);

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void showUserWall() {
        String username = "Charlie";
        String command = createCommandString(username, CommandParser.WALL_COMMAND_STRING);

        Command expectedCommand = new WallCommand(username);
        Command actualCommand = CommandParser.parse(command);

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    /*
        The following commands don't have something to really test for, so
        the tests will simply ensure the returned instances are of the
        correct type.
     */
    @Test
    public void unknownCommand() {
        String command = "dummy xuuyi öjkjlk";
        Command actualCommand = CommandParser.parse(command);

        Assert.assertTrue(actualCommand instanceof UnknownCommand);
    }

    @Test
    public void quitCommandFound() {
        Command actual = CommandParser.parse("quit");

        Assert.assertTrue(actual instanceof QuitCommand);
    }

    /**
     * Return the command line, needed to execute the given command.
     * <p>
     * Example:
     * We want to see the wall of the user 'Alice', the needed command string is:
     * Alice wall
     *
     * @param username User, the command goes with
     * @param command  The command itself
     * @return The corresponding command string
     */
    private String createCommandString(String username, String command) {
        String commandString = username + " " + command;
        return commandString;
    }

    /**
     * Many commands need additional information, beside the obligatory username.
     * This method lets you create command strings for these kind of commands.
     * <p>
     * Example:
     * The user 'Bob' will follow the user 'Charlie', the needed command string is:
     * Bob follows Charlie
     *
     * @param username          The user, the command goes with (in the example, this would be "Bob")
     * @param command           The command itself (in the example, this is
     *                          {@link technology.steinhauer.demo.valtech.commands.CommandParser#FOLLOW_COMMAND_STRING})
     * @param additionalContent The additional information, needed by the command (in the example, this should
     *                          be 'Charlie')
     * @return
     */
    private String createCommandString(String username, String command, String additionalContent) {
        String commandString = username + " " + command + " " + additionalContent;
        return commandString;
    }
}
