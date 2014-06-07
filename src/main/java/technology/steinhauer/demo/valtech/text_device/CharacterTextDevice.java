package technology.steinhauer.demo.valtech.text_device;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * Created by hsteinhauer on 07.06.14.
 */
public class CharacterTextDevice implements TextDevice {

    private PrintWriter writer;
    private BufferedReader reader;

    public CharacterTextDevice(BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Reader reader() {
        return reader;
    }

    public PrintWriter writer() {
        return writer;
    }

    public void format(String format, Object... values) {
        writer.printf(format, values);
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }
}
