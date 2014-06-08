package technology.steinhauer.demo.valtech.commands;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the functions of CommandParser class.
 */
public class CommandParserTest {

    @Test
    public void quitCommandFound () {
        Command actual = CommandParser.parse("quit");

        Assert.assertTrue(actual instanceof QuitCommand);
    }

    @Test
    public void showUserTimeline () {
        String expectedUsername = "username";
        Command actual = CommandParser.parse(expectedUsername);

        String actualUsername = actual.getUsername();
        Assert.assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void postMessage() {
        String username = "username";
        String message = "demo";
        String command = username + " -> " + message;
        PostCommand expectedCommand = new PostCommand(username, message);

        Command actualCommand = CommandParser.parse(command);

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void followUser () {
        String username = "Charlie";
        String follow = "Alice";
        String command = username + " follows " + follow;
        Command expectedCommand = new FollowCommand(username, follow);

        Command actualCommand = CommandParser.parse(command);

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void showUserWall () {
        String username = "Charlie";
        String command = username + " wall";
        Command expectedCommand = new WallCommand(username);

        Command actualCommand = CommandParser.parse(command);

        Assert.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void unknownCommand() {
        String command = "";
        Command actualCommand = CommandParser.parse(command);

        // there is nothing we can compare two different instances of UnknownCommand - the type is the only thing
        // that's really important.
        Assert.assertTrue(actualCommand instanceof UnknownCommand);
    }
}
