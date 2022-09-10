package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.Undead;
import game.enums.Abilities;

import java.util.Random;

/**
 * A class that represents cemetery which the Undead will be spawned from.
 */
public class Cemetery extends Ground {

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor.
     */
    public Cemetery() {
        super('c');
    }

    /**
     * Returns true when an Actor can enter this location.
     * Actors can enter a location if the cemetery is passable and there isn't an Actor there already.
     *
     * @param actor The Actor who might be moving
     * @return True if the Actor can enter this location; otherwise False
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Abilities.CANNOT_ENTER_CEMETERY);
    }

    /**
     * The thing that Cemetery will do every turn.
     * Here, every turn there is a 25% possibility that an Undead will be spawned.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if ((rand.nextInt(100) <= 25) && !location.containsAnActor()) {
            location.addActor(new Undead("Undead"));
        }
    }
}
