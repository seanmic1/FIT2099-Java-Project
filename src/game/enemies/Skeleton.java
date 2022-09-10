package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actions.EnemyDeathAction;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

import java.util.Random;

/**
 * A Skeleton.
 */
public class Skeleton extends Enemy implements Resettable, Soul {

    /**
     * rand: Random number generator
     * spawnPoint: The location of the Skeleton that will be reallocated when the world is reset
     * waitTurns: The waiting turn of the Skeleton after the world is reset
     * live: The number of live that the Skeleton has (It might be revived)
     */
    private Random rand = new Random();
    private Location spawnPoint;
    private int waitTurns = -1;
    private int live = 2;

    /**
     * Constructor.
     *
     * @param name The name of the Skeleton
     * @param spawnPoint The initial location of the Skeleton
     */
    public Skeleton(String name, Location spawnPoint) {
        super(name, 's', 100);
        this.spawnPoint = spawnPoint;
        behaviours.add(new WanderBehaviour());

        if (rand.nextInt(100) <= 50) {
            this.addItemToInventory(new Broadsword());
        }
        else {
            this.addItemToInventory(new GiantAxe());
        }

        this.registerInstance();
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
        if (!isConscious()){
            return new EnemyDeathAction(this);
        }

        // stop moving for one turn after the world reset
        if (this.hasCapability(Status.STUNNED)) {
            if (waitTurns < 0) {
                waitTurns = 1;
            }
            else {
                waitTurns -= 1;
                if (waitTurns == 0) {
                    this.removeCapability(Status.STUNNED);
                }
            }

            return new DoNothingAction();
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
     * Reset everything of this Skeleton when the world is reset.
     *
     * @param map The current map
     */
    @Override
    public void resetInstance(GameMap map) {
        this.behaviours.clear();
        this.hitPoints = this.maxHitPoints;
        this.behaviours.add(new WanderBehaviour());
        spawnPoint.map().moveActor(this,spawnPoint);
        this.addCapability(Status.STUNNED);
        waitTurns = -1;
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
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject A target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(250);
    }

    /**
     * Getter of live.
     *
     * @return The live of the Skeleton
     */
    public int getLive() {
        return live;
    }

    /**
     * Setter of live
     */
    public void setLive(int live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.hitPoints + "/" + this.maxHitPoints + ") (" + this.getWeapon() + ")";
    }
}
