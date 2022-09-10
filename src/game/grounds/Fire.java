package game.grounds;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player;
import game.actions.EnemyDeathAction;
import game.actions.KillPlayerAction;
import game.enemies.Enemy;
import game.weapons.YhormsGreatMachete;

/**
 * Class that represents a fire in the game, and it can hurt actor.
 */
public class Fire extends Ground {

    /**
     * turns: The number of turns that the fire will be staying on the ground
     */
    private int turns;

    /**
     * Constructor.
     */
    public Fire() {
        super('V');
        this.turns = 3;
    }

    /**
     * The thing that Fire will do every turn.
     * Here, every turn if there is an Actor on top of it, it will hurt the Actor.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // hurt Actor on top of this location except the Actor who holds the weapon of YhormsGreatMachete
        if (location.containsAnActor() && !(location.getActor().getWeapon() instanceof YhormsGreatMachete)) {
            location.getActor().hurt(25);

            // we check the death of Actor here because tick() will be executed after the playTurn() where death
            // otherwise be checked. If we didn't check here, the Actor would be alive for another turn even they are died.
            if (location.getActor() instanceof Enemy && !(location.getActor().isConscious())) {
                EnemyDeathAction deathAction = new EnemyDeathAction((Enemy)location.getActor());
                deathAction.execute(location.getActor(), location.map());
            }
            else if (location.getActor() instanceof Player && !(location.getActor().isConscious())) {
                KillPlayerAction killPlayerAction = new KillPlayerAction();
                killPlayerAction.execute(location.getActor(), location.map());
            }
        }

        // Decrease the number of turns
        this.turns -= 1;
        if (this.turns < 0) {
            location.setGround(new Dirt());
        }
    }
}
