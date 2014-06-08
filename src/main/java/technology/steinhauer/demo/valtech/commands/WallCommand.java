package technology.steinhauer.demo.valtech.commands;

/**
 * Represents the command to show a wall of a specific user.
 *
 */
public class WallCommand extends Command {

    public WallCommand(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallCommand that = (WallCommand) o;

        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
