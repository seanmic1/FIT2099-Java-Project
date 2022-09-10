package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Soul;

/**
 * The boss of Design o' Souls.
 */
public abstract class LordOfCinder extends Enemy {

    /**
     * Record the transformation of the boss.
     */
    private boolean transformed;

    /**
     * Constructor.
     *
     * @param name The name of LordOfCinder
     * @param displayChar The character that will represent the LordOfCinder in the display
     * @param hitPoints   The LordOfCinder's starting hit points
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints );
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions Collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn
     * @param map The map containing the Actor
     * @param display The I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject A target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(5000);
    }

    /**
     * Setter of transformed.
     */
    public void transform() {
        this.transformed = true;
    }

    /**
     * Getter of transformed.
     *
     * @return True if the boss is transformed; False otherwise
     */
    public boolean isTransformed() {
        return transformed;
    }

    /**
     * Getter of name.
     *
     * @return The name of the LordOfCinder
     */
    public String getName() {
        return name;
    }
}
