package technology.steinhauer.demo.valtech.commands;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class FollowCommand extends Command {

    private final String follow;

    public FollowCommand(String username, String follow) {
        this.username = username;
        this.follow = follow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowCommand that = (FollowCommand) o;

        if (!follow.equals(that.follow)) return false;
        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = follow.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
