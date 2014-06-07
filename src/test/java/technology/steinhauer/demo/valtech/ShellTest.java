package technology.steinhauer.demo.valtech;

import org.junit.Assert;
import org.junit.Test;

import java.io.Console;

/**
 * Created by hsteinhauer on 06.06.14.
 */
public class ShellTest {

    @Test
    public void getConsole () {
        SocialShell shell = new SocialShell();

        Console console = shell.getConsole();

        Assert.assertNotNull(console);
    }
}
