package technology.steinhauer.demo.valtech.text_device;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * Created by hsteinhauer on 07.06.14.
 */
public interface TextDevice {

    /**
     * Returns the reader instance, used by the
     * current device.
     *
     * @return Reader instance
     */
    Reader reader();

    /**
     * Returns the writer instance, used by the
     * current device.
     *
     * @return Current Writer instance
     */
    PrintWriter writer();

    /**
     * Formats the given values as described in
     * format and writes it to the text device.
     *
     * @param format Format instructions for the given values
     * @param numberToFormat Values to format and print
     */
    void printf(String format, Object... values);

    /**
     * Waits for an input - usually some kind of user input - and
     * returns it as one string.
     *
     * @return The input we got
     * @throws IOException Thrown if any error during the input occurs.
     */
    String readLine() throws IOException;

    String readLine(String prompt) throws IOException;

    void println(String s);
}
