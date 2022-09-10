package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enemies.YhormTheGiant;
import game.interfaces.Chargeable;

/**
 * Wind Slash Action for Storm Ruler weapon.
 */
public class WindSlashAction extends WeaponAction {

    /**
     * weapon: A weapon which is chargeable
     * direction: The direction of the target
     * target: The target to attack
     */
    Chargeable weapon;
    String direction;
    Actor target;

    /**
     * Constructor.
     *
     * @param weaponItem The weapon item that has this capability
     * @param direction The direction of the target
     * @param target The target to attack
     */
    public WindSlashAction(Chargeable weaponItem, String direction, Actor target) {
        super((WeaponItem) weaponItem);
        this.weapon = weaponItem;
        this.direction = direction;
        this.target = target;
    }

    /**
     * Perform the Action.
     * Here, YhormTheGiant will be attacked and stun for one turn.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String retString;

        Action action = new AttackAction(target, direction, actor.getWeapon().damage()*2,100);
        ((YhormTheGiant)target).setStunCount(2);
        retString = action.execute(actor, map);
        weapon.resetCharge();

        return retString + "\n"+ actor + " stun " + target + " (Wind Slash)";
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " stuns Yhorm the Giant (Wind Slash Action)";
    }
}
