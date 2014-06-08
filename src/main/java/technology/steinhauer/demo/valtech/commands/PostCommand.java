package technology.steinhauer.demo.valtech.commands;

import java.util.Date;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class PostCommand extends Command {
    public PostCommand(String username, String message) {
        this.username = username;
        this.message = message;
        this.date = new Date();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (date != null ? !date.equals(command.date) : command.date != null) return false;
        if (message != null ? !message.equals(command.message) : command.message != null) return false;
        if (!username.equals(command.username)) return false;

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
