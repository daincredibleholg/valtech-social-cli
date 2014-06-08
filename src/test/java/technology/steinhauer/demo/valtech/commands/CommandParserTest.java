package technology.steinhauer.demo.valtech.commands;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by hsteinhauer on 08.06.14.
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
}
