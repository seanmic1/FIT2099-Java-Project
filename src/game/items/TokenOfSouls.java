package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.interfaces.Soul;

/**
 * A class representing TokenOfSouls in the game.
 */
public class TokenOfSouls extends Item implements Soul {

    /**
     * The number of souls that this token contains
     */
    private int souls;

    /**
     * Constructor.
     */
    public TokenOfSouls() {
        super("Token of Souls", '$', false);
    }

    /**
     * Constructor.
     *
     * @param souls The number of souls to store in the TokenOfSouls
     */
    public TokenOfSouls(int souls) {
        super("Token of Souls", '$', false);
        this.souls = souls;
    }

    /**
     * Create and return an action to pick this Item up.
     *
     * @param actor An actor that will interact with this item
     * @return A new PickUpItemAction if this Item is portable, null otherwise
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpItemAction(this);
    }

    /**
     * Inform a carried Item of the passage of time.
     * This method is called once per turn, if the Item is being carried.
     *
     * @param currentLocation The location of the actor carrying this Item
     * @param actor The actor carrying this Item
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.asSoul().transferSouls(actor.asSoul());
        actor.removeItemFromInventory(this);
    }

    /**
     * Increase souls to current instance's souls.
     *
     * @param souls The number of souls to be incremented
     * @return Transaction status. True if addition successful, otherwise False
     */
    @Override
    public boolean addSouls(int souls) {
        this.souls += souls;
        return true;
    }

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject A target souls
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(this.souls);
        this.souls = 0;
    }

    @Override
    public String toString() {
        return "Token of Souls (" + this.souls + ")";
    }
}
