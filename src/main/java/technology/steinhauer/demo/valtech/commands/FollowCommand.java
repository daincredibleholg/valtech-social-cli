package technology.steinhauer.demo.valtech.commands;

import technology.steinhauer.demo.valtech.entities.Follower;
import technology.steinhauer.demo.valtech.persistence.FollowerManager;

/**
 * This commmand encapsulates the command line input "<follower> follows <followee>".
 *
 */
public class FollowCommand extends Command {

    private final String followee;

    public FollowCommand(String username, String followee) {
        this.username = username;
        this.followee = followee;
    }

    @Override
    public void execute() {
        Follower follower = new Follower(username, followee);
        FollowerManager.saveFollower(follower);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowCommand that = (FollowCommand) o;

        if (!followee.equals(that.followee)) return false;
        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = followee.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
