package text_engine.characters;

import java.io.BufferedReader;
import java.io.PrintWriter;

import text_engine.boundaries.Room;
import text_engine.constants.Actions;
import text_engine.interaction.ExitToException;
import text_engine.interaction.actions.ActionList;
import text_engine.items.BaseGameEntity;
import text_engine.items.Item;

public class PlayerCharacter extends GameCharacter {

    public class ExitToPlayerCharacterException extends ExitToException {

    }

    /**
     * Constructs a {@link PlayerCharacter} in the given location, with the given inventory.
     *
     * @param location  The room the player is in
     * @param inventory The list of items in the {@link GameCharacter}'s inventory
     */
    public PlayerCharacter(String name, String description, Room location, Item... inventory) {
        super(name, description, location, inventory);
    }

    @Override
    protected ActionList actions(GameCharacter actor, BaseGameEntity from,
                                 BufferedReader reader, PrintWriter writer) {
        ActionList actions = new ActionList();
        // For now, PlayerCharacter is the root entity, so it can't go "back".

        actions.add(Actions.LOOK_AROUND);
        actions.add(Actions.EXIT_GAME);
        actions.add(Actions.OPEN_INVENTORY);

        return actions;
    }

    /**
     * Constructs a {@link PlayerCharacter} with an empty inventory.
     *
     * @param location The room the player is in
     */
    public PlayerCharacter(String name, String description, Room location) {
        super(name, description, location);
    }


    @Override
    protected int
    respondToInteraction(PlayerCharacter actor, BaseGameEntity from,
                         BufferedReader reader, PrintWriter writer) throws ExitToException {
        return super.respondToInteraction(actor, from, reader, writer);
    }
}
