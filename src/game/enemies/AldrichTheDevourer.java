package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Player;
import game.actions.AttackAction;
import game.actions.LordOfCinderDeathAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.RangeAttackBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.weapons.DarkmoonLongBow;

import java.util.HashSet;
import java.util.Set;

/**
 * A class represents Aldrich the Devourer which is one of the Lord of Cinder.
 */
public class AldrichTheDevourer extends LordOfCinder implements Resettable, Soul {

    /**
     * spawnPoint: The initial location of AldrichTheDevourer
     */
    private Location spawnPoint;

    /**
     * Constructor.
     *
     * @param spawnPoint The initial location of the AldrichTheDevourer
     */
    public AldrichTheDevourer(Location spawnPoint) {
        super("Aldrich the Devourer", 'A', 350);
        this.addItemToInventory(new DarkmoonLongBow());
        this.spawnPoint = spawnPoint;
        this.registerInstance();
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * Here, if the Player is within adjacent square, AttackBehaviour and FollowBehaviour will be added into behaviour.
     *
     * @param otherActor The Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map Current GameMap
     * @return A collection of Actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
            behaviours.clear();
            behaviours.add(new AttackBehaviour(otherActor, direction));
            behaviours.add(new FollowBehaviour(otherActor));
        }

        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions Collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn.
     * @param map The map containing the Actor
     * @param display The I/O object to which messages may be written
     * @return Action to perform on the current turn
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            return new LordOfCinderDeathAction(this);
        }

        Set<Location> locations = new HashSet<>();
        Set<Location> noCheckLocations = new HashSet<>();
        Location actorLocation = map.locationOf(this);
        // check for three-square away
        for (Exit exit : actorLocation.getExits()) {
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
                    behaviours.clear();
                    behaviours.add(new RangeAttackBehaviour(location.getActor()));
                    behaviours.add(new FollowBehaviour(location.getActor()));
                }
            }
        }

        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    /**
     * Reset everything of this AldrichTheDevourer when the world is reset.
     *
     * @param map The current map
     */
    @Override
    public void resetInstance(GameMap map) {
        this.hitPoints = this.maxHitPoints;
        behaviours.clear();
        spawnPoint.map().moveActor(this, spawnPoint);
    }

    /**
     * To state whether this Actor will still be existing when the world is reset.
     *
     * @return True
     */
    @Override
    public boolean isExist() {
        return true;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.hitPoints + "/" + this.maxHitPoints + ") (" + this.getWeapon() + ")";
    }
}
