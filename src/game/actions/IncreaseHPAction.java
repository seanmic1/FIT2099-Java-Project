package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Increase HP Action for Player to purchase from the Vendor.
 */
public class IncreaseHPAction extends PurchaseAction {

    /**
     * Perform the Action.
     * Here, if the action is executed, Player's hit points will be increased (If souls are enough).
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String str;
        int cost = 200;
        if (checkSouls(actor,cost)) {
            actor.increaseMaxHp(25);
            str = "Increased max hp of " + actor + " by 25";
        }
        else {
            str = actor + " does not enough souls to increase HP! (Needs " + countDeficit(actor,cost) + " more souls)";
        }

        return str;
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys Max HP modifier (+25HP): 200 souls";
    }
}
