package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actions.LordOfCinderDeathAction;
import game.actions.StunnedAction;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.EmberFormBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.interfaces.Stunnable;
import game.weapons.YhormsGreatMachete;

/**
 * A class represents Yhorm the Giant which is one of the Lord of Cinder.
 */
public class YhormTheGiant extends LordOfCinder implements Resettable, Soul, Stunnable {

    /**
     * stunCount: The number of turn that YhormTheGiant is been stunned
     * spawnPoint: The initial location of YhormTheGiant
     */
    private int stunCount;
    private Location spawnPoint;

    /**
     * Constructor.
     *
     * @param spawnPoint The initial location of the YhormTheGiant
     */
    public YhormTheGiant(Location spawnPoint) {
        super("Yhorm the Giant", 'Y', 500);
        this.addItemToInventory(new YhormsGreatMachete());
        this.stunCount = 0;
        this.spawnPoint = spawnPoint;
        this.registerInstance();
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * Here, if the Player is within adjacent square, EmberFormBehaviour, FollowBehaviour and AttackBehaviour will be
     * added into behaviour.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction String representing the direction of the other Actor
     * @param map Current GameMap
     * @return A collection of Actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
            behaviours.clear();
            behaviours.add(new EmberFormBehaviour(this));
            behaviours.add(new FollowBehaviour(otherActor));
            behaviours.add(new AttackBehaviour(otherActor,direction));
        }

        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions Collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn
     * @param map The map containing the Actor
     * @param display The I/O object to which messages may be written
     * @return Action to perform on the current turn
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            return new LordOfCinderDeathAction(this);
        }

        if (this.getStunCount() > 0) {
            this.decrementStunCount();
            return new StunnedAction(this);
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
     * Reset everything of this YhormTheGiant when the world is reset.
     *
     * @param map The current map
     */
    @Override
    public void resetInstance(GameMap map) {
        this.hitPoints = this.maxHitPoints;
        behaviours.clear();
        spawnPoint.map().moveActor(this,spawnPoint);
    }

    /**
     * To state whether this Actor will still be existing when the world is reset.
     *
     * @return True
     */
    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * Setter of stunCount.
     *
     * @param stunCount The number of turn that the actor get stun
     */
    @Override
    public void setStunCount(int stunCount) {
        this.stunCount = stunCount;
    }

    /**
     * Reduce the number of stunCount.
     */
    @Override
    public void decrementStunCount() {
        this.stunCount -= 1;
    }

    /**
     * Getter of stunCount.
     *
     * @return The number of turn that the actor get stun
     */
    @Override
    public int getStunCount() {
        return this.stunCount;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.hitPoints + "/" + this.maxHitPoints + ") (" + this.getWeapon() + ")";
    }
}
