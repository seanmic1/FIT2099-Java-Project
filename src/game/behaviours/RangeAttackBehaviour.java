package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
import game.Player;
import game.actions.AttackAction;
import game.actions.BlockAttackPathAction;
import game.interfaces.Behaviour;

import java.util.HashSet;
import java.util.Set;

/**
 * RangeAttackBehaviour (Action) that acts as an Action for Player but a behaviour of Enemy which can attack Actor
 * within 3-square away.
 */
public class RangeAttackBehaviour extends Action implements Behaviour {

    /**
     * target: The target to attack
     * targetLocation: The location of the target
     */
    private Actor target;
    private Location targetLocation;

    /**
     * Constructor.
     *
     * @param otherActor The target to attack
     */
    public RangeAttackBehaviour(Actor otherActor) {
        this.target = otherActor;
    }

    /**
     * Constructor.
     *
     * @param otherActor The target to attack
     * @param targetLocation The location of the target
     */
    public RangeAttackBehaviour(Actor otherActor, Location targetLocation) {
        this.target = otherActor;
        this.targetLocation = targetLocation;
    }

    /**
     * A factory for creating actions for Player.
     * Here, we create AttackAction if there is no wall between the attack path; otherwise BlockAttackPathAction.
     *
     * @param actor The Actor acting
     * @param map The GameMap containing the Actor
     * @return An Action that actor can perform, or null if actor can't do this
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Set<Location> locations = new HashSet<>();
        Set<Location> noCheckLocations = new HashSet<>();
        Location selfLocation = map.locationOf(actor);
        Location targetLocation = map.locationOf(target);
        // check for three-square away
        for (Exit exit : selfLocation.getExits()) {
            noCheckLocations.add(exit.getDestination());
            for (Exit exit2 : exit.getDestination().getExits()) {
                for (Exit exit3 : exit2.getDestination().getExits())
                    locations.add(exit3.getDestination());
            }
        }
        // remove duplicate locations
        locations.removeAll(noCheckLocations);
        for (Location location : locations) {
            if (location.containsAnActor()) {
                if (location.getActor() instanceof Player) {
                    NumberRange xs, ys;
                    // check whether the attack path contains wall or not, if yes then return BlockAttackPathAction
                    if (targetLocation.x() == selfLocation.x() || targetLocation.y() == selfLocation.y()) {
                        xs = new NumberRange(Math.min(targetLocation.x(), selfLocation.x()),
                                        Math.abs(targetLocation.x() - selfLocation.x()) + 1);
                        ys = new NumberRange(Math.min(targetLocation.y(), selfLocation.y()),
                                        Math.abs(targetLocation.y() - selfLocation.y()) + 1);
                        for (int x : xs) {
                            for (int y : ys) {
                                if(map.at(x, y).getGround().blocksThrownObjects())
                                    return new BlockAttackPathAction();
                            }
                        }
                    }

                    return new AttackAction(target, location.toString());
                }
            }
        }

        return null;
    }

    /**
     * Perform the Action (For Enemy).
     * Execute AttackAction if there is no wall within the attack path; otherwise print out a line of string that
     * represents the attack is blocked.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Set<Location> locations = new HashSet<>();
        Set<Location> noCheckLocations = new HashSet<>();
        Location actorLocation = map.locationOf(actor);
        Location selfLocation = map.locationOf(target);
        for (Exit exit : actorLocation.getExits()) {
            noCheckLocations.add(exit.getDestination());
            for (Exit exit2 : exit.getDestination().getExits()) {
                for (Exit exit3 : exit2.getDestination().getExits())
                    locations.add(exit3.getDestination());
            }
        }
        locations.removeAll(noCheckLocations);
        for (Location location : locations) {
            if (location.containsAnActor()) {
                if (location.getActor() instanceof Player) {
                    NumberRange xs, ys;
                    if (selfLocation.x() == actorLocation.x() || selfLocation.y() == actorLocation.y()) {
                        xs = new NumberRange(Math.min(selfLocation.x(), actorLocation.x()),
                                        Math.abs(selfLocation.x() - actorLocation.x()) + 1);
                        ys = new NumberRange(Math.min(selfLocation.y(), actorLocation.y()),
                                        Math.abs(selfLocation.y() - actorLocation.y()) + 1);
                        for (int x : xs) {
                            for (int y : ys) {
                                if(map.at(x, y).getGround().blocksThrownObjects())
                                    return actor + "miss the shot because of the wall";
                            }
                        }
                    }
                    return new AttackAction(target, location.toString()).execute(actor, map);
                }
            }
        }

        return null;
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return  actor + " attacks " + target + " at (" + targetLocation.x() + " " + targetLocation.y() + ")";
    }
}
