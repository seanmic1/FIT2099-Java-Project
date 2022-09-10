package game.weapons;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.SpinAttackAction;

/**
 * A class that represent GiantAxe weapon in the game.
 */
public class GiantAxe extends WeaponItem {

    /**
     * Constructor.
     */
    public GiantAxe() {
        super("Giant Axe", 'X', 50, "swings", 80);
        this.portable = false;
        this.allowableActions = new Actions(new SpinAttackAction(50));
    }
}
