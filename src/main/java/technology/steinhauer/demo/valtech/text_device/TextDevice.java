package technology.steinhauer.demo.valtech.text_device;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * During automated tests, in IDEs and on some rare operating systems
 * is no Console support available. Therefor a small indirection layer
 * was implemented, providing the needed Console methods also, if there
 * is no Console support.
 *
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
     * @param values Values to format and print
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

    /**
     * Same as {@link TextDevice#readLine()} but lets you display
     * a short prompt in front of the input line.
     *
     * @param prompt Prompt to show
     * @return The input we got
     * @throws IOException Thrown in case of an IO problem.
     */
    String readLine(String prompt) throws IOException;

    /**
     * Prints out the given string to the console.
     *
     * @param s String to show
     */
    void println(String s);
}
