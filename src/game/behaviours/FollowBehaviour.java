package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import game.interfaces.Behaviour;

/**
 * A class that figures out a MoveAction that will move the actor one step closer to a target Actor.
 */
public class FollowBehaviour implements Behaviour {

	/**
	 * target: The target to follow
	 */
	private Actor target;

	/**
	 * Constructor.
	 * 
	 * @param subject The Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
	 * A factory for creating actions.
	 *
	 * @param actor The Actor acting
	 * @param map The GameMap containing the Actor
	 * @return An Action that actor can perform, or null if actor can't do this.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}

		return null;
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a The first location
	 * @param b The first location
	 * @return The number of steps between a and b if you only move in the four cardinal directions
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}
