package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.actions.EnemyDeathAction;
import game.interfaces.Soul;
import game.items.TokenOfSouls;

import java.util.Random;

/**
 * A class represents a Mimic that might spawn from the Chest.
 */
public class Mimic extends Enemy {

    /**
     * Random number generator
     */
    Random rand = new Random();

    /**
     * Constructor.
     */
    public Mimic() {
        super("Mimic", 'M', 100);
        this.registerInstance();
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions Collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map The map containing the Actor
     * @param display The I/O object to which messages may be written
     * @return Action to perform on the current turn
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            int numOfTokens = rand.nextInt(3) + 1;
            for (int i=0; i<numOfTokens; i++) {
                map.locationOf(this).addItem(new TokenOfSouls(100));
            }
            display.println("Mimic drops " + numOfTokens + " tokens");
            return new EnemyDeathAction(this);
        }

        // loop through all behaviours
        for(game.interfaces.Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * @return A freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "kicks");
    }

    /**
     * Reset everything of this Mimic when the world is reset.
     *
     * @param map The current map
     */
    @Override
    public void resetInstance(GameMap map) {
        map.removeActor(this);
    }

    /**
     * To state whether this Actor will still be existing when the world is reset.
     *
     * @return False
     */
    @Override
    public boolean isExist() {
        return false;
    }

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject A target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(200);
    }

    @Override
    public String toString() {
        return this.name + " (" + this.hitPoints + "/" + this.maxHitPoints + ") (no weapon)";
    }
}
