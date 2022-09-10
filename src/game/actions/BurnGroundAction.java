package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.Player;
import game.grounds.Dirt;
import game.grounds.Fire;

import java.util.List;

/**
 * Burn Ground Action when the Actor is holding the weapon of Yhorms Great Machete (Active skill).
 */
public class BurnGroundAction extends Action {

    /**
     * Perform the Action.
     * Here, when this action is executed, the ground of adjacent square will be set to Fire ground.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // burning the ground
        List<Exit> exits = map.locationOf(actor).getExits();
        for (Exit exit: exits) {
            if (exit.getDestination().getGround() instanceof Dirt) {
                exit.getDestination().setGround(new Fire());
            }
        }

        if (actor instanceof Player) {
            return actor + " burns the surrounding";
        }
        else {
            return actor + " burns the area and skin is engulfed with fire (Ember Form)";
        }
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " burns surrounding";
    }
}
