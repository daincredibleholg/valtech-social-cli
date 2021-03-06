package technology.steinhauer.demo.valtech.commands;

import technology.steinhauer.demo.valtech.entities.Post;
import technology.steinhauer.demo.valtech.persistence.PostManager;

import java.util.Date;

/**
 * Represents post command to a users timeline
 */
public class PostCommand extends Command {

    private final String message;

    public PostCommand(String username, String message) {
        this.username = username;
        this.message = message;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void execute() {
        Post post = new Post(username, message, date);
        PostManager.savePost(post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostCommand that = (PostCommand) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
