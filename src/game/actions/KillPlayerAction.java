package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.ResetManager;
import game.items.TokenOfSouls;

/**
 * Kill PLayer Action when the player has a die Action.
 */
public class KillPlayerAction extends Action {

    /**
     * Perform the Action.
     * Here, if Player execute this action, a TokenOfSouls will be added in the current die location and the world will
     * be reset.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // create a token of souls when player die
        Location deathLocation = map.locationOf(actor);
        TokenOfSouls tokenOfSouls = new TokenOfSouls();
        actor.asSoul().transferSouls(tokenOfSouls);
        deathLocation.addItem(tokenOfSouls);

        // run reset manager
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(map);

        return """
                ▄██   ▄    ▄██████▄  ███    █▄       ████████▄   ▄█     ▄████████ ████████▄ \s
                ███   ██▄ ███    ███ ███    ███      ███   ▀███ ███    ███    ███ ███   ▀███\s
                ███▄▄▄███ ███    ███ ███    ███      ███    ███ ███▌   ███    █▀  ███    ███\s
                ▀▀▀▀▀▀███ ███    ███ ███    ███      ███    ███ ███▌  ▄███▄▄▄     ███    ███\s
                ▄██   ███ ███    ███ ███    ███      ███    ███ ███▌ ▀▀███▀▀▀     ███    ███\s
                ███   ███ ███    ███ ███    ███      ███    ███ ███    ███    █▄  ███    ███\s
                ███   ███ ███    ███ ███    ███      ███   ▄███ ███    ███    ███ ███   ▄███\s
                 ▀█████▀   ▀██████▀  ████████▀       ████████▀  █▀     ██████████ ████████▀ \s
                The world has reset...
               """;
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return Null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
