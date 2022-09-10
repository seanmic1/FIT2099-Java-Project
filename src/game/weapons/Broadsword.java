package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Passives;

/**
 * A class of that represent BroadSword weapon in the game.
 */
public class Broadsword extends WeaponItem {

    /**
     * Constructor.
     */
    public Broadsword() {
        super("Broadsword", 'S', 30, "slashes", 80);
        this.portable = false;
    }

    /**
     * Add passives skill of CRITICAL_STRIKE to Actor's capability if they are holding it.
     * This method is called once per turn, if the Item is being carried.
     *
     * @param currentLocation The location of the actor carrying this Item
     * @param actor The actor carrying this Item
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (!actor.hasCapability(Passives.CRITICAL_STRIKE)) {
            actor.addCapability(Passives.CRITICAL_STRIKE);
        }
    }
}
