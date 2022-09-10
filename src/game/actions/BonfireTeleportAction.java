package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.grounds.Bonfire;

/**
 * Bonfire Teleport Action when Actor is interacting with Bonfire (Must have TELEPORT Abilities).
 */
public class BonfireTeleportAction extends Action {

    /**
     * An instance of Bonfire
     */
    private Bonfire bonfire;

    /**
     * Constructor.
     *
     * @param bonfire An instance of Bonfire
     */
    public BonfireTeleportAction(Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    /**
     * Perform the Action.
     * Here, Player will be moved to the Bonfire that it choose to teleport.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location teleportLocation = bonfire.getLocation();
        teleportLocation.map().moveActor(actor,teleportLocation);
        return actor + " teleports to " + bonfire.getName();
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves to " + bonfire.getName();
    }
}
