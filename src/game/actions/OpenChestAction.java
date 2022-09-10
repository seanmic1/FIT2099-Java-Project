package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.enemies.Mimic;
import game.items.TokenOfSouls;

import java.util.Random;

/**
 * Open Chest Action for Actor that has Ability of OPEN_CHEST.
 */
public class OpenChestAction extends Action {

    /**
     * spawnPoint: The location of the Chest
     * chest: An instance of Actor (Chest)
     */
    private Location spawnPoint;
    private Actor chest;

    /**
     * Constructor.
     *
     * @param chest An instance of Actor (Chest)
     * @param spawnPoint The location of the Chest
     */
    public OpenChestAction(Actor chest, Location spawnPoint) {
        this.spawnPoint = spawnPoint;
        this.chest = chest;
    }

    /**
     * Perform the Action.
     * Here, when the action is executed, it might turn out a Mimic or a number of TokenOfSouls.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random rand = new Random();
        if (rand.nextInt(100) <= 50) {
            // drop 1 to 3 TokenOfSouls
            int numOfTokens = rand.nextInt(3) + 1;
            for (int i=0; i<numOfTokens; i++) {
                spawnPoint.addItem(new TokenOfSouls(100));
            }
            map.removeActor(chest);
            return "Chest drops " + numOfTokens + " tokens of souls on the ground.";
        }
        else {
            // become a Mimic
            map.removeActor(chest);
            Mimic mimic = new Mimic();
            spawnPoint.addActor(mimic);
            return new AttackAction(actor).execute(mimic,map);
        }
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens Chest";
    }
}
