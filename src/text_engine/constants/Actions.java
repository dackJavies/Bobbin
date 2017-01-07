package text_engine.constants;

import text_engine.boundaries.Door;
import text_engine.characters.GameCharacter;
import text_engine.interaction.Interactive;
import text_engine.interaction.Printers;
import text_engine.interaction.actions.BaseAction;
import text_engine.items.BaseGameEntity;
import text_engine.items.Item;

public class Actions {

    public static final BaseAction LOOK_AROUND =
            new BaseAction(Globals.messages.getString("Actions.LOOK_AROUND.name"),
                           Globals.messages.getString("Actions.LOOK_AROUND.description"),
                           GameCharacter::getLocation);

    public static final BaseAction OPEN_INVENTORY =
            new BaseAction(Globals.messages.getString("Actions.OPEN_INVENTORY.name"),
                           "",
                           GameCharacter::getInventory);

    public static final BaseAction EXIT_GAME =
            new BaseAction(Globals.messages.getString("Actions.EXIT_GAME.name"),
                           "",
                           Interactive::exitGame);

    public static BaseAction BACK(BaseGameEntity from) {
        return new BaseAction(Globals.messages.getString("Actions.BACK.name"),
                              "",
                              playerCharacter -> from);
    }

    public static BaseAction OPEN_DOOR(Door door) {
        return new BaseAction(Printers.format("Actions.OPEN_DOOR", door.getName()),
                              door.getDescription(),
                              playerCharacter -> door);
    }

    public static BaseAction ITEM(Item item) {
        return new BaseAction(item.getName(),
                              item.getDescription(),
                              playerCharacter -> item);
    }
}
