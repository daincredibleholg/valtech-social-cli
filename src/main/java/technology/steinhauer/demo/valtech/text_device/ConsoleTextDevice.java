package technology.steinhauer.demo.valtech.text_device;

import sun.reflect.annotation.ExceptionProxy;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * Created by hsteinhauer on 07.06.14.
 */
public class ConsoleTextDevice implements TextDevice {
    private Console console;

    public ConsoleTextDevice(Console console) throws Exception {
        this.console = console;
        checkConsoleIsNotNull();
    }

    public ConsoleTextDevice() throws Exception {
        this.console = System.console();
        checkConsoleIsNotNull();
    }

    private void checkConsoleIsNotNull () throws Exception {
        if (this.console == null) {
            throw new Exception("No Console available");
        }
    }

    @Override
    public Reader reader() {
        return console.reader();
    }

    @Override
    public PrintWriter writer() {
        return console.writer();
    }

    @Override
    public void format(String format, Object... values) {
        console.printf(format, values);
    }

    @Override
    public String readLine() throws IOException {
        return console.readLine();
    }
}
