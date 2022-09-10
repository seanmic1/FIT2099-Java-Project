package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Player;
import game.ResetManager;
import game.grounds.Bonfire;

/**
 * Use Bonfire Action for the Player.
 */
public class UseBonfireAction extends Action {

    /**
     * bonfire: An instance of Bonfire
     */
    private Bonfire bonfire;

    /**
     * Constructor.
     *
     * @param bonfire An instance of Bonfire
     */
    public UseBonfireAction(Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    /**
     * Perform the Action.
     * Here, if this action is executed, the world will be reset.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        // used casting but only Player will be able to use bonfire
        ((Player)actor).setSpawnPoint(map.locationOf(actor));
        resetManager.run(map);
        return actor + " rests at " + bonfire.getName();
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at " + bonfire.getName();
    }
}
