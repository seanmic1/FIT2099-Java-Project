package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.ValleyDeathAction;

/**
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	/**
	 * Constructor.
	 */
	public Valley() {
		super('+');
	}

	/**
	 * Return False where every Actor cannot enter this location.
	 * Player can still enter into Valley and die, but we are not doing the checking in here (checked in Action)
	 *
	 * @param actor The Actor to check
	 * @return False or actor cannot enter
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Returns an Action list of the Player can choose to do when Player is interacting with valley.
	 *
	 * @param actor The Actor acting this action
	 * @param location The current Location of the Actor
	 * @param direction The direction of the Ground from the Actor
	 * @return A new collection of Actions
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		actions.add(new ValleyDeathAction(direction));
		return actions;
	}

	/**
	 * Add the given Capability to this Ground.
	 *
	 * @param capability The Capability to add
	 */
	@Override
	public void addCapability(Enum<?> capability) {
		super.addCapability(capability);
	}
}
