package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.enemies.Enemy;

import java.util.List;

/**
 * Spin Attack Action for Giant Axe weapon.
 */
public class SpinAttackAction extends AttackAction {

    /**
     * spinDamage: The damage to the Actor
     */
    private int spinDamage;

    /**
     * Constructor.
     *
     * @param weaponDamage The initial damage of the weapon
     */
    public SpinAttackAction(int weaponDamage) {
        this.spinDamage = weaponDamage / 2;
    }

    /**
     * Perform the Action.
     * Here, an AttackAction will be executed if this method is been called.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String retString = actor + " uses Spin Attack!";

        List<Exit> exits = map.locationOf(actor).getExits();
        Actor target = map.getActorAt(exits.get(0).getDestination());
        if (target instanceof Enemy) {
            Action attackAction = new AttackAction(target, exits.get(0).getName(), spinDamage);
            retString += "\n" + attackAction.execute(actor, map);
        }

        StringBuilder ret_stringBuilder = new StringBuilder(retString);
        for (int i=1; i<exits.size(); i++) {
            target = map.getActorAt(exits.get(i).getDestination());
            if (target instanceof Enemy) {
                Action attackAction = new AttackAction(target, exits.get(i).getName(), spinDamage);
                ret_stringBuilder.insert(0, attackAction.execute(actor, map) + "\n");
            }
        }

        retString = ret_stringBuilder.toString();
        return retString;
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks surrounding (Spin Attack Action)";
    }
}
