package technology.steinhauer.demo.valtech.commands;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class CommandParser {
    public static Command parse(String commandInput) {
        Command result = null;
        if (commandInput == "quit") {
            result = new QuitCommand();
        } else {
            result = new TimelineCommand(commandInput);
        }
        return result;
    }
}
