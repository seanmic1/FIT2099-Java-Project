package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.interfaces.Chargeable;
import game.weapons.StormRuler;

/**
 * Charge Action for Storm Ruler weapon.
 */
public class ChargeAction extends WeaponAction {

    /**
     * weapon: A weapon which is chargeable
     */
    Chargeable weapon;

    /**
     * Constructor.
     *
     * @param weaponItem The weapon item that has chargeable capability
     */
    public ChargeAction(Chargeable weaponItem) {
        super((WeaponItem) weaponItem);
        weapon = weaponItem;
    }

    /**
     * Perform the Action.
     * Here, the weapon will be charged for once when been executed.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        weapon.charge();
        return actor + " charges Storm Ruler";
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " charges Storm Ruler (" + weapon.getCharges() + "/" + weapon.getMaxCharges() + ")";
    }
}
