package technology.steinhauer.demo.valtech.commands;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class QuitCommand extends Command {
    @Override
    public void execute() {
        System.exit(0);
    }
}
