package technology.steinhauer.demo.valtech.commands;

import java.util.Date;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public abstract class Command {
    protected String username = "";
    protected String message;
    protected Date date;

    public String getUsername() {
        return this.username;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getDate() {
        return date;
    }

}
