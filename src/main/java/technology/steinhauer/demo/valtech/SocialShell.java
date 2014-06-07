package technology.steinhauer.demo.valtech;

import java.io.Console;

/**
 * Created by hsteinhauer on 06.06.14.
 */
public class SocialShell {
    private Console console;

    public Console getConsole() {
        console = System.console();
        return console;
    }
}
