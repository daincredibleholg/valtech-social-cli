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
}
