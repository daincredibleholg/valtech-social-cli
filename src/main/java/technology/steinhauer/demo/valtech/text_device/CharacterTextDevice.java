package technology.steinhauer.demo.valtech.text_device;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * This implementation of TextDevice is mainly needed for the automated
 * tests, where no Console is available. Beside that, it is a good
 * fallback for OSes not supporting Java's Console feature.
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

    public void printf(String format, Object... values) {
        writer.printf(format, values);
        writer.flush();
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    @Override
    public String readLine(String prompt) throws IOException {
        String format = "%s";
        printf(format, prompt);
        return readLine();
    }

    @Override
    public void println(String s) {
        writer().println(s);
    }
}
