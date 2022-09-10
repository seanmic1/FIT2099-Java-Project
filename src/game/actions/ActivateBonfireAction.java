package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;
import game.grounds.Bonfire;

/**
 * Activate Bonfire Action when the Player is first time interact with the Bonfire.
 */
public class ActivateBonfireAction extends Action {

    /**
     * bonfire: An instance of Bonfire
     */
    private Bonfire bonfire;

    /**
     * Constructor.
     *
     * @param bonfire An instance of Bonfire
     */
    public ActivateBonfireAction(Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    /**
     * Perform the Action.
     * Here, if Player execute this action, the PLayer spawn point will be reset to the latest interact bonfire location.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        bonfire.setIsLit();
        ((Player)actor).setSpawnPoint(map.locationOf(actor));
        return "\n" +
                "██████   ██████  ███    ██ ███████ ██ ██████  ███████     ██      ██ ████████ \n" +
                "██   ██ ██    ██ ████   ██ ██      ██ ██   ██ ██          ██      ██    ██    \n" +
                "██████  ██    ██ ██ ██  ██ █████   ██ ██████  █████       ██      ██    ██    \n" +
                "██   ██ ██    ██ ██  ██ ██ ██      ██ ██   ██ ██          ██      ██    ██    \n" +
                "██████   ██████  ██   ████ ██      ██ ██   ██ ███████     ███████ ██    ██    \n";
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " lights " + bonfire.getName();
    }
}
