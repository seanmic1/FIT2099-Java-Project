package game.weapons;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.BurnGroundAction;

import java.util.List;

/**
 * A class that represent YhormsGreatMachete weapon in the game.
 */
public class YhormsGreatMachete extends WeaponItem {

    /**
     * Constructor.
     */
    public YhormsGreatMachete() {
        super("Yhorm's Great Machete", '7', 95, "slashes", 60);
        this.portable = false;
    }

    /**
     * Constructor.
     *
     * @param hitRate The hit rate of this weapon
     */
    public YhormsGreatMachete(int hitRate) {
        super("Yhorm's Great Machete", '7', 95, "slashes", hitRate);
        this.portable = false;
    }

    /**
     * Getter of allowableActions.
     *
     * @return A list of allowable actions when the Actor is holding it
     */
    @Override
    public List<Action> getAllowableActions() {
        allowableActions = new Actions(new BurnGroundAction());
        return allowableActions.getUnmodifiableActionList();
    }
}
