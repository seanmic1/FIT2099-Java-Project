package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.actions.AttackAction;
import game.actions.EnemyDeathAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

/**
 * Abstract class used to represent enemies in the game.
 */
public abstract class Enemy extends Actor implements Soul, Resettable {

    /**
     * behaviours: List of behaviours that the Enemy has
     */
    ArrayList<Behaviour> behaviours;

    /**
     * Constructor.
     *
     * @param name The name of the Enemy
     * @param displayChar The character that will represent the Enemy in the display
     * @param hitPoints   The Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours = new ArrayList<>();
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * Here, if the Player is within adjacent square, FollowBehaviour and AttackBehaviour will be added into behaviour.
     *
     * @param otherActor The Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map Current GameMap
     * @return A collection of Actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            behaviours.clear();
            behaviours.add(new FollowBehaviour(otherActor));
            behaviours.add(new AttackBehaviour(otherActor,direction));
        }

        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions Collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map The map containing the Actor
     * @param display The I/O object to which messages may be written
     * @return Action to perform on the current turn
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            return new EnemyDeathAction(this);
        }

        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * Getter of hitPoints.
     *
     * @return The hit points of the Enemy
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * Getter of maxHitPoints.
     *
     * @return The maximum hit points of the Enemy
     */
    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }
}
