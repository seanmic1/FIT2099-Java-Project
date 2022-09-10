package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponItem;
import game.enemies.AldrichTheDevourer;
import game.enemies.YhormTheGiant;
import game.items.CinderOfLord;
import game.weapons.DarkmoonLongBow;
import game.weapons.YhormsGreatMachete;

/**
 * Trade Cinder of Lord Action for Player to trade a weapon from Vendor.
 */
public class TradeCinderOfLordAction extends PurchaseAction {

    /**
     * cinderOfLord: An instance of CinderOfLord
     * weapon: An instance of WeaponItem
     */
    private CinderOfLord cinderOfLord;
    private WeaponItem weapon;

    /**
     * Constructor.
     *
     * @param cinderOfLord An instance of CinderOfLord
     */
    public TradeCinderOfLordAction(CinderOfLord cinderOfLord) {
        this.cinderOfLord = cinderOfLord;
        if (cinderOfLord.getLordOfCinder() instanceof YhormTheGiant) {
            weapon = new YhormsGreatMachete();
        }
        else if (cinderOfLord.getLordOfCinder() instanceof AldrichTheDevourer) {
            weapon = new DarkmoonLongBow();
        }
    }

    /**
     * Perform the Action.
     * Here, the CinderOfLord will be removed from the Inventory but the corresponds weapon will be swapped with the old
     * weapon.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(cinderOfLord);
        removeWeapon(actor);
        actor.addItemToInventory(weapon);
        return actor + " traded Cinder of " + cinderOfLord.getLordOfCinder() + " for " + weapon;
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades Cinder of " + cinderOfLord.getLordOfCinder().getName();
    }
}
