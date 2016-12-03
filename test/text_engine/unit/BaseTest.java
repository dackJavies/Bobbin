package text_engine.unit;

import text_engine.boundaries.Door;
import text_engine.boundaries.Room;
import text_engine.characters.GameCharacter;
import text_engine.characters.PlayerCharacter;
import text_engine.constants.Items;
import text_engine.items.Key;

/**
 * Base class for all tests. Gives the basic environment that most tests will require.
 */
public class BaseTest {

    protected final Room room1 = new Room("Room 1", "Starting room, attached to Room 2.");
    protected final Room room2 = new Room("Room 2", "Attached to Room 1.");
    protected final Room room3 = new Room("Room 3", "Attached to Room 2.");

    protected final Door door1Room1Room2Unlocked = new Door(false, room1, room2);
    protected final Door door2Room2Room3Locked = new Door(true, room2, room3);

    protected final Key keyDoor1 = door1Room1Room2Unlocked.makeKey("D1 Key", "Door 1 Key");
    protected final Key keyDoor2 = door2Room2Room3Locked.makeKey("D2 Key", "Door 2 Key");

    protected final PlayerCharacter playerCharacter
            = new PlayerCharacter("Player Character",
                                  "The main Player Character, initially in Room 1.",
                                  room1,
                                  Items.getCopiesOf(Items.BLUEBERRY, Items.FLOUR, Items.SUGAR,
                                                    Items.WATER, Items.BED));

    protected final GameCharacter gameCharacter
            = new GameCharacter("Generic Game Character",
                                "A generic GameCharacter, initially in Room 2.",
                                room2,
                                Items.getCopyOf(Items.WATER));
}
