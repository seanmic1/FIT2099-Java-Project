package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enemies.Enemy;

/**
 * Enemy Death Action for Enemy like Skeleton and Undead (Not for boss).
 */
public class EnemyDeathAction extends Action {

    /**
     * enemy: An instance of Enemy
     */
    private Enemy enemy;

    /**
     * Constructor.
     *
     * @param enemy An instance of Enemy
     */
    public EnemyDeathAction(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Perform the Action.
     * Here, the enemy will be removed from the current map.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " is killed.";
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
