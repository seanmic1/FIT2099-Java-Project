package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.actions.AttackAction;
import game.Player;
import game.interfaces.Behaviour;

import java.util.List;

/**
 * Attack Behavior that will attack the actor.
 */
public class AttackBehaviour implements Behaviour {

    /**
     * target: The target to attack
     * direction: The direction of the target
     */
    private Actor target;
    private String direction;

    /**
     *Constructor.
     *
     * @param otherActor The target to attack
     * @param direction The direction of the target
     */
    public AttackBehaviour(Actor otherActor, String direction) {
        this.target = otherActor;
        this.direction = direction;
    }

    /**
     * A factory for creating actions.
     * Here, we create AttackAction if the target is within adjacent square; otherwise create DoNothingAction.
     *
     * @param actor The Actor acting
     * @param map The GameMap containing the Actor
     * @return An Action that actor can perform, or null if actor can't do this
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Exit> exits = map.locationOf(actor).getExits();
        for (Exit exit: exits) {
            if (exit.getDestination().getActor() instanceof Player) {
                return new AttackAction(target, direction);
            }
        }
        return new DoNothingAction();
    }
}
