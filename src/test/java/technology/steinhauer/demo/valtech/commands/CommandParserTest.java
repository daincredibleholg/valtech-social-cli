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
    public void usernameOnlyProducesTimelineCommand () {
        Command actual = CommandParser.parse("username");

        Assert.assertTrue(actual instanceof TimelineCommand);
    }
    @Test
    public void showUserTimeline () {
        String expectedUsername = "username";
        Command actual = CommandParser.parse(expectedUsername);

        String actualUsername = actual.getUsername();
        Assert.assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void showOtherUsersTimeline() {
        String expectedUsername = "otheruser";
        Command actual = CommandParser.parse(expectedUsername);

        String actualUsername = actual.getUsername();
        Assert.assertEquals(expectedUsername, actualUsername);
    }

    
}
