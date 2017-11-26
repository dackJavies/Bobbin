package bobbin.usability;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class BufferedUserInput {

    private StringBuilder sb;

    /**
     * Initialise a new {@link BufferedUserInput} with the given {@link String} as the starting input.
     *
     * @param s {@link String} to be added to the {@link BufferedUserInput}
     */
    public BufferedUserInput(String s) {
        sb = new StringBuilder(Objects.requireNonNull(s));
        newLine();
    }

    /**
     * Initialise a new {@link BufferedUserInput} with no input.
     */
    public BufferedUserInput() {
        sb = new StringBuilder();
    }

    /**
     * Append a string to the user input.
     *
     * @param s {@link String} to be appended
     * @return {@link this}
     */
    public BufferedUserInput append(String s) {
        sb.append(s);
        return this;
    }

    /**
     * Append an {@code int} to the user input.
     *
     * @param i {@code int} to be appended
     * @return {@link this}
     */
    public BufferedUserInput append(int i) {
        sb.append(i);
        return this;
    }

    /**
     * Append a platform-dependent newline character sequence to the user input.
     *
     * @return {@link this}
     */
    public BufferedUserInput newLine() {
        return append(System.lineSeparator());
    }

    /**
     * Append a string followed by a platform-dependent newline character sequence.
     *
     * @param s {@link String} to be appended
     * @return {@link this}
     */
    public BufferedUserInput appendLine(String s) {
        return append(s).newLine();
    }

    /**
     * Append an {@code int} followed by a platform-dependent newline character sequence.
     *
     * @param i {@code int} to be appended
     * @return {@link this}
     */
    public BufferedUserInput appendLine(int i) {
        return append(i).newLine();
    }


    /**
     * Append a {@link Collection<String>} of user inputs.
     *
     * @param list user inputs to append
     * @return {@link this}
     */
    public BufferedUserInput appendAllOnNewLines(Collection<String> list) {
        for (String s : list) {
            appendLine(s);
        }
        return this;
    }

    /**
     * Append an array of user inputs.
     *
     * @param list user inputs to append
     * @return {@link this}
     */
    public BufferedUserInput appendAllOnNewLines(String... list) {
        return appendAllOnNewLines(Arrays.asList(list));
    }

    /**
     * Build a {@link BufferedReader} from {@link this}.
     *
     * @return {@link BufferedReader} whose contents include what has been added to {@link this}
     */
    public BufferedReader build() {
        return new BufferedReader(new StringReader(sb.toString()));
    }
}