package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.items.EstusFlask;

/**
 * Use Estus Flask Action for the Player to drink Estus Flask.
 */
public class UseEstusFlaskAction extends Action {

    /**
     * maxHitPoints: The maximum hit points of the Player
     * estusFlask: The Estus Flask item that the PLayer holds
     */
    private int maxHitPoints;
    private EstusFlask estusFlask;

    /**
     * Constructor.
     *
     * @param maxHitPoints The maximum hit points of the Player
     * @param estusFlask The Estus Flask item that the PLayer holds
     */
    public UseEstusFlaskAction(int maxHitPoints, EstusFlask estusFlask) {
        this.maxHitPoints = maxHitPoints;
        this.estusFlask = estusFlask;
    }

    /**
     * Perform the Action.
     * Here, the Player's hit points will be healed by 40% of the max hit points.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String str;
        if (estusFlask.canDrink()) {
            int heal_amount = (int) Math.floor(maxHitPoints * 0.4);
            actor.heal(heal_amount);
            estusFlask.removeCharge();
            str = actor + " drinks Estus Flask";
        }
        else {
            str = actor + " has no more charges in Estus Flask!";
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
        return actor + " drinks Estus Flask (" + estusFlask.getCharges() + "/" + estusFlask.getMaxCharges() + ")";
    }
}
