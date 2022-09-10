package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Abilities;

/**
 * Exit Buy Menu Action when the Player want to exit the Purchase Action.
 */
public class ExitBuyMenuAction extends PurchaseAction {

    /**
     * Perform the Action.
     * Here, when this action is executed, Player's purchase ability will be removed and move ability will be added.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(Abilities.BUY);
        actor.addCapability(Abilities.MOVE);
        return actor + " exits the Vendor's buy menu";
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " exits the Vendor's buy menu";
    }
}
