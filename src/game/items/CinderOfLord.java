package game.items;

import edu.monash.fit2099.engine.Item;
import game.enemies.LordOfCinder;

/**
 * A class that represents Cinder of Lord that will be dropped by the LordOfCinder when they are defeated.
 */
public class CinderOfLord extends Item {

    /**
     * The instance of LordOfCinder
     */
    private LordOfCinder lordOfCinder;

    /***
     * Constructor.
     *
     * @param lordOfCinder The instance of LordOfCinder
     */
    public CinderOfLord(LordOfCinder lordOfCinder) {
        super("Cinder of " + lordOfCinder.getName(), '%', true);
        this.lordOfCinder = lordOfCinder;
    }

    /**
     * Getter of lordOfCinder.
     *
     * @return The instance of lordOfCinder
     */
    public LordOfCinder getLordOfCinder() {
        return lordOfCinder;
    }
}
