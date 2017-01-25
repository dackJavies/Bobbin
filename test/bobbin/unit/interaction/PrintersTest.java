package bobbin.unit.interaction;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import bobbin.constants.Globals;
import bobbin.interaction.Printers;
import bobbin.items.Inventory;
import bobbin.items.Item;
import bobbin.unit.BaseUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PrintersTest extends BaseUnitTest {

    private final ByteArrayOutputStream stream = new ByteArrayOutputStream();
    private final PrintWriter writer = new PrintWriter(stream);

    private void commonListAssertions(List list, String response, boolean ordered) {
        assertTrue("should have first item", response.contains(list.get(0).toString()));
        assertTrue("should have last item", response.contains(list.get(list.size() - 1).toString()));

        if (ordered) {
            assertTrue("should not have number prefixes", response.contains("1. "));
            assertFalse("should have dash prefixes", response.startsWith("- "));
        } else {
            assertFalse("should not have number prefixes", response.contains("1. "));
            assertTrue("should have dash prefixes", response.startsWith("- "));
        }
    }

    @Test
    public void printUnordered_list() {
        List inventory = playerCharacter.getInventory();
        //noinspection unchecked
        Printers.printUnordered(writer, inventory);
        final String response = new String(stream.toByteArray());

        commonListAssertions(inventory, response, false);
    }

    @Test
    public void printOrdered_list() {
        Inventory list = playerCharacter.getInventory();
        Printers.printOrdered(writer, list);
        final String response = new String(stream.toByteArray());

        commonListAssertions(list, response, true);
    }

    @Test
    public void printOrdered_array() {
        Item[] array = playerCharacter.getInventory().toArray();
        Printers.printOrdered(writer, array);
        final String response = new String(stream.toByteArray());

        commonListAssertions(Arrays.asList(array), response, true);
    }

    @Test
    public void print() {
        Item item = playerCharacter.getInventory().get(0);
        Printers.print(writer, item);

        final String response = new String(stream.toByteArray());
        assertEquals(item.toString() + System.lineSeparator(), response);
    }

    @Test
    public void printBooleanPrompt() {
        Boolean[] defaultResponses = new Boolean[] {null, false, true};
        String[] expectedOptions = new String[] {
                Globals.messages.getString("Prompts.booleanOptions"),
                Globals.messages.getString("Prompts.booleanOptions_noDefault"),
                Globals.messages.getString("Prompts.booleanOptions_yesDefault")};
        String prompt = "yes or no?";

        for (int i = 0; i < defaultResponses.length; i++) {
            Printers.printBooleanPrompt(writer, prompt, defaultResponses[i]);
            final String response = new String(stream.toByteArray());
            assertTrue(response.contains(prompt));
            assertTrue(response.contains(expectedOptions[i]));
        }
    }
}