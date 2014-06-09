package technology.steinhauer.demo.valtech.commands;

import java.util.Date;

/**
 * This is the base implementation for every command, we will support.
 * Each command will have a username and a date, when the command was
 * produced.
 * The main idea behind this structure is to have a streamlined way to
 * provide specialised Command classes. These classes are following
 * the Command pattern.
 */
public abstract class Command {
    protected String username = "";
    protected Date date;

    public String getUsername() {
        return this.username;
    }
    public Date getDate() {
        return date;
    }

    public abstract void execute();

}
