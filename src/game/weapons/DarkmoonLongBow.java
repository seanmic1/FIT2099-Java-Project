package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Abilities;
import game.enums.Passives;

/**
 * A class that represents Darkmoon LongBow weapon in the game.
 */
public class DarkmoonLongBow extends WeaponItem {

    /**
     * Constructor.
     */
    public DarkmoonLongBow() {
        super("Darkmoon LongBow", 'D', 70, "pierces", 80);
        this.portable = false;
    }

    /**
     * Add Abilities of RANGED_ATTACK and Passives skill of CRITICAL_HIT to Actor's capability if they are holding it.
     * This method is called once per turn, if the Item is being carried.
     *
     * @param currentLocation The location of the actor carrying this Item
     * @param actor The actor carrying this Item
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (!(actor.hasCapability(Abilities.RANGED_ATTACK))) {
            actor.addCapability(Abilities.RANGED_ATTACK);
        }
        if (!(actor.hasCapability(Passives.CRITICAL_HIT))) {
            actor.addCapability(Passives.CRITICAL_HIT);
        }
    }
}
