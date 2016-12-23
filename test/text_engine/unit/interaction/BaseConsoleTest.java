package text_engine.unit.interaction;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import text_engine.unit.BaseTest;

import static org.junit.Assert.assertTrue;

public class BaseConsoleTest extends BaseTest {

    protected final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    protected final PrintWriter writer = new PrintWriter(baos);

    void assertPromptOutput(String prompt) {
        final String consoleOutput = new String(baos.toByteArray());
        assertTrue(consoleOutput.contains(prompt));
    }

}