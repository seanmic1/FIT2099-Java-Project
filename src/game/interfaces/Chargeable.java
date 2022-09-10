package game.interfaces;

import edu.monash.fit2099.engine.Weapon;

/**
 * A contract for chargeable instance (weapon).
 * Classes that implement Chargeable must implement all the methods here.
 */
public interface Chargeable extends Weapon {

    /**
     * Increase the charge count of the weapon.
     */
    void charge();

    /**
     * Reset the charge count of the weapon.
     */
    void resetCharge();

    /**
     * Getter of chargeCount.
     *
     * @return The times of chargeCount
     */
    int getCharges();

    /**
     * Getter of chargeMax.
     *
     * @return The maximum charge count of the weapon
     */
    int getMaxCharges();
}
