package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.BurnGroundAction;
import game.enemies.LordOfCinder;
import game.enemies.YhormTheGiant;
import game.interfaces.Behaviour;
import game.weapons.YhormsGreatMachete;

import java.util.List;

/**
 * Ember Form Behaviour acts as an Action and a Behaviour that will execute some actions that make the YhormTheGiant
 * become stronger (Ember Form).
 */
public class EmberFormBehaviour extends Action implements Behaviour {

    /**
     * The Lord of Cinder (boss) in this game
     */
    private LordOfCinder lordOfCinder;

    /**
     * Constructor.
     *
     * @param lordOfCinder The Lord of Cinder (boss) in this game
     */
    public EmberFormBehaviour(LordOfCinder lordOfCinder) {
        this.lordOfCinder = lordOfCinder;
    }

    /**
     * A factory for creating actions.
     * Here, we create and return an ember form action only once if the boss hit points is below half of the max hit
     * points; otherwise null.
     *
     * @param actor The Actor acting
     * @param map The GameMap containing the Actor
     * @return An Action that actor can perform, or null if actor can't do this
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if ((lordOfCinder.getHitPoints() <= lordOfCinder.getMaxHitPoints()/2) && !lordOfCinder.isTransformed()) {
            lordOfCinder.transform();
            if (actor instanceof YhormTheGiant) {
                return this;
            }
        }

        return null;
    }

    /**
     * Perform the Action.
     * Here, when this action is executed, a higher hit rate same weapon will replace the old one and burn the ground.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // remove the current weapon and added a new weapon with higher hit rate
        List<Item> items = List.copyOf(actor.getInventory());
        for (Item item: items) {
            if (item instanceof WeaponItem) {
                actor.removeItemFromInventory(item);
            }
        }
        actor.addItemToInventory(new YhormsGreatMachete(90));

        // burning the ground
        return new BurnGroundAction().execute(actor, map);
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return Null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
