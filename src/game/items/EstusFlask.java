package game.items;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Player;
import game.actions.UseEstusFlaskAction;
import game.interfaces.Resettable;

/**
 * A class that represents Estus Flask in the game.
 */
public class EstusFlask extends Item implements Resettable {

    /**
     * maxCharges: The maximum charges that an Estus Flask can have
     * charges: The current charges of the Estus Flask
     * player: The instance of Player
     */
    private int maxCharges = 3;
    private int charges = 3;
    private Player player;

    /**
     * Constructor.
     *
     * @param player Player that holds Estus Flask
     */
    public EstusFlask(Player player) {
        super("Estus Flask", 'E', false);
        this.player = player;
        allowableActions = new Actions(new UseEstusFlaskAction(this.player.getMaxHitPoints(), this));
        this.registerInstance();
    }

    /**
     * Used to check the available charges in Estus Flask.
     *
     * @return True if charges more than 0; False otherwise
     */
    public boolean canDrink() {
        return charges > 0;
    }

    /**
     * Getter of maxCharges.
     *
     * @return The maximum number of charges
     */
    public int getMaxCharges() {
        return maxCharges;
    }

    /**
     * Getter of current charges.
     *
     * @return The current charges
     */
    public int getCharges() {
        return charges;
    }

    /**
     * Used to remove a charge after estus flask is consumed.
     */
    public void removeCharge() {
        this.charges -= 1;
    }

    /**
     * Used to reset charges of estus flask.
     *
     * @param map The current map
     */
    @Override
    public void resetInstance(GameMap map) {
        this.charges = this.maxCharges;
    }

    /**
     * To state whether this Item will still be existing when the world is reset.
     *
     * @return True
     */
    @Override
    public boolean isExist() {
        return true;
    }
}
