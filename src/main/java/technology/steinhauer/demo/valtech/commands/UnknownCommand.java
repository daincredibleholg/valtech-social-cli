package technology.steinhauer.demo.valtech.commands;

/**
 * Just for the case, there is an unmappable command coming in, this is
 * a dummy command, simply doing nothing.
 */
public class UnknownCommand extends Command {

    @Override
    public void execute() {
        // Do nothing here
    }
}
