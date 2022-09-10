package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Behaviour;

import java.util.Random;

/**
 * Random Die Behaviour acts as an Action and a Behaviour that Undead will die random every turn.
 */
public class RandomDieBehaviour extends Action implements Behaviour {

    /**
     * A factory for creating actions.
     * Here, we create and return a kill self action (10% of possibility); otherwise null.
     *
     * @param actor The Actor acting
     * @param map The GameMap containing the Actor
     * @return An Action that actor can perform, or null if actor can't do this
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Random rand = new Random();
        if (rand.nextInt(100) <= 10) {
            return this;
        }

        return null;
    }

    /**
     * Perform the Action.
     * Here, when the action is executed, the Undead will be removed from the current map (Die randomly).
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " dies randomly.";
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
