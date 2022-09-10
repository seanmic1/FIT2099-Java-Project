package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	/**
	 * Constructor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Returns true if an Actor can enter this location.
	 * Actors can enter a location if the floor is passable and there isn't an Actor there already.
	 *
	 * @param actor The Actor who might be moving
	 * @return True if the Actor can enter this location; otherwise False
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return actor.hasCapability(Abilities.CAN_ENTER_FLOOR_FOGDOOR);
	}
}
