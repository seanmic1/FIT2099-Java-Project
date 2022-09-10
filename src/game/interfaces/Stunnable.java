package game.interfaces;

/**
 * A contract for stunnable instance (Actor).
 * Classes that implement Stunnable must implement all the method here.
 */
public interface Stunnable {

    /**
     * Setter of stunCount.
     *
     * @param stunCount The number of turn that the actor get stun
     */
    void setStunCount(int stunCount);

    /**
     * Reduce the number of stunCount.
     */
    void decrementStunCount();

    /**
     * Getter of stunCount.
     *
     * @return The number of turn that the actor get stun
     */
    int getStunCount();
}
