package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Stunnable;

/**
 * Stunned Action for the Enemy (boss).
 */
public class StunnedAction extends Action {

    /**
     * actor: An actor which is stunnable
     */
    Stunnable actor;

    public StunnedAction(Stunnable actor){
        this.actor = actor;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " is stunned!";
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
