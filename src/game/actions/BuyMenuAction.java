package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Abilities;

/**
 * Buy Menu Action when the Player enter to buy from Vendor.
 */
public class BuyMenuAction extends Action {

    /**
     * Perform the Action.
     * Here, when this action is executed, Player's move ability will be removed and purchase ability will be added.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(Abilities.MOVE);
        actor.addCapability(Abilities.BUY);
        return actor + " opens the Vendor's buy menu";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys from Vendor";
    }
}
