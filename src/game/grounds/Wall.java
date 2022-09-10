package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A class representing wall in game.
 */
public class Wall extends Ground {

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Returns false where every Actor cannot enter this location.
	 *
	 * @param actor The Actor who might be moving
	 * @return False where every Actor cannot enter this location
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Override this to implement wall that blocks thrown objects but not movement, or vice versa.
	 *
	 * @return True
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
