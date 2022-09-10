package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.interfaces.Behaviour;

import java.util.ArrayList;
import java.util.Random;

/**
 * Wander Behaviour that will make enemies to move around.
 */
public class WanderBehaviour extends Action implements Behaviour {

	/**
	 * Random number generator.
	 */
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor The Actor enacting the behaviour
	 * @param map The map that actor is currently on
	 * @return An Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}
	}

	/**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action
	 * @param map The map the actor is on
	 * @return A description of what happened that can be displayed to the user
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 * Returns a descriptive string.
	 *
	 * @param actor The actor performing the action
	 * @return The text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
