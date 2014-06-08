package technology.steinhauer.demo.valtech;

import org.junit.Assert;
import org.junit.Test;

import java.io.Console;
import java.io.PrintWriter;

/**
 * Created by hsteinhauer on 06.06.14.
 */
public class ShellTest {

    @Test
    public void testPrintWriter () {
        PrintWriter writer = new PrintWriter(System.out);
        writer.print(">");
        writer.flush();
    }
}
