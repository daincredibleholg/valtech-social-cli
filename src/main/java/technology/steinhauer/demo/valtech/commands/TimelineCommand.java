package technology.steinhauer.demo.valtech.commands;

import technology.steinhauer.demo.valtech.Post;
import technology.steinhauer.demo.valtech.PostPrinter;
import technology.steinhauer.demo.valtech.TimelineService;
import technology.steinhauer.demo.valtech.text_device.TextDevice;
import technology.steinhauer.demo.valtech.text_device.TextDeviceFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by hsteinhauer on 08.06.14.
 */
public class TimelineCommand extends Command {

    public TimelineCommand (String username)  {
        this.username = username;
    }

    @Override
    public void execute() {
        PostPrinter.printTimeline(username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimelineCommand that = (TimelineCommand) o;

        if (!date.equals(that.date)) return false;
        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
