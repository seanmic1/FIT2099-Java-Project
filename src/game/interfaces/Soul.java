package game.interfaces;

import edu.monash.fit2099.engine.addons.DesignOSoulsAddOn;

/**
 * A contract for soul-able instance (in other words, object/instance that has souls).
 * The instance can be Actor, Item, or even Ground.
 * @see DesignOSoulsAddOn
 */
public interface Soul {

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject A target souls
     */
    void transferSouls(Soul soulObject);

    /**
     * Increase souls to current instance's souls.
     * By default, it cannot increase the souls.
     * You may override this method to implement its functionality.
     *
     * @param souls Number of souls to be incremented
     * @return Transaction status. True if addition successful, otherwise False
     */
    default boolean addSouls(int souls) {
        return false;
    }

    /**
     * Allow other classes to deduct the number of this instance's souls
     * By default, an instance cannot get its own souls subtracted.
     * You may override this method to conduct subtraction on current souls.
     *
     * @param souls Number souls to be deducted
     * @return Transaction status. True if subtraction successful, otherwise False
     */
    default boolean subtractSouls(int souls) {
        return false;
    }

    /**
     * Default method that returns default number of souls.
     *
     * @return Integer number of souls at start of game
     */
    default int getSouls() {
        return 0;
    }
}
