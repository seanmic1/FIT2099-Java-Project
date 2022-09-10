package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.UseFogDoorAction;
import game.enums.Abilities;

/**
 * A class that represents fog door which allow the Player travels to other map.
 */
public class FogDoor extends Ground {

    /**
     * Constructor.
     */
    public FogDoor() {
        super('=');
    }

    /**
     * Returns true if an Actor can enter this location.
     * Actors can enter a location if the fog door is passable and there isn't an Actor there already.
     *
     * @param actor The Actor who might be moving
     * @return True if the Actor can enter this location; otherwise False
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Abilities.CAN_ENTER_FLOOR_FOGDOOR);
    }

    /**
     * Returns an Action list of the PLayer can choose to do when PLayer is interacting with fog door.
     *
     * @param actor The Actor acting these actions
     * @param location The current Location of the Actor
     * @param direction The direction of the Ground from the Actor
     * @return A new collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();

        if (location.map().locationOf(actor).getGround() instanceof FogDoor) {
            if (actor.hasCapability(Abilities.CAN_ENTER_FLOOR_FOGDOOR)) {
                actions.add(new UseFogDoorAction());
            }
        }

        return actions;
    }
}
