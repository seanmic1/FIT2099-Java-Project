package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    STUNNED,          // use this capability to show that actor is stunned (YhormTheGiant)
    EMBER_FORM        // use this capability to show that actor is in Ember Form (LordOfCinder)
}
