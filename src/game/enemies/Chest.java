package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actions.OpenChestAction;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * A class represents Chest in the game.
 */
public class Chest extends Enemy implements Resettable {

    /**
     * spawnPoint: The initial location of Chest
     */
    private final Location spawnPoint;

    /**
     * Constructor.
     *
     * @param spawnPoint The initial location of the Chest located
     */
    public Chest(Location spawnPoint) {
        super("Chest", '?','1');
        this.spawnPoint = spawnPoint;
        this.registerInstance();
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
        return new DoNothingAction();
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     * Here, if the Player is within adjacent square, an OpenChestAction is return to Player.
     *
     * @param otherActor The Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map Current GameMap
     * @return A collection of Actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new OpenChestAction(this,spawnPoint));
    }

    /**
     * Reset everything of this Chest when the world is reset.
     *
     * @param map The current map
     */
    @Override
    public void resetInstance(GameMap map) {
        if (!(map.contains(this))) {
            if (map.isAnActorAt(spawnPoint)) {
                map.removeActor(map.getActorAt(spawnPoint));
            }
            map.addActor(this, spawnPoint);
        }
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
    public void transferSouls(Soul soulObject) {}
}
