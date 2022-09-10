package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Buy Weapon Action for Player when entered Purchase Action.
 */
public class BuyWeaponAction extends PurchaseAction {

    /**
     * weapon: The weapon that the Player choose to buy
     * soulsCost: The cost of the weapon
     */
    private WeaponItem weapon;
    private int soulsCost;

    /**
     * Constructor.
     *
     * @param weapon The weapon that the Player choose to buy
     * @param soulsCost The cost of the weapon
     */
    public BuyWeaponAction(WeaponItem weapon, int soulsCost) {
        this.weapon = weapon;
        this.soulsCost = soulsCost;
    }

    /**
     * Perform the Action.
     * Here, when the action is executed, a new weapon will replace the old weapon (If souls are enough).
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String str;
        if (checkSouls(actor, soulsCost)) {
            removeWeapon(actor);
            actor.addItemToInventory(weapon);
            str = actor + " buys " + weapon + " for " + soulsCost + " souls";
        }
        else {
            str = actor + " does not enough souls! (Needs "+ countDeficit(actor, soulsCost) + " more souls)";
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
        return actor + " buys " + weapon + " (" + soulsCost + " souls)";
    }
}
