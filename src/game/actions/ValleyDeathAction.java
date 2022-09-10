package game.actions;

import edu.monash.fit2099.engine.Actor;

/**
 * Valley Death Action for the Player when he jumps into the Valley.
 */
public class ValleyDeathAction extends KillPlayerAction {

    /**
     * hotKey: The hotkey to execute the corresponding action
     */
    protected String hotkey;

    /**
     *Constructor.
     *
     * @param direction The direction of the Player
     */
    public ValleyDeathAction(String direction) {
        switch (direction) {
            case "North-West" -> hotkey = "7";
            case "North"-> hotkey = "8";
            case "North-East"-> hotkey = "9";
            case "East"-> hotkey = "6";
            case "South-East"-> hotkey = "3";
            case "South" -> hotkey = "2";
            case "South-West"-> hotkey = "1";
            case "West"-> hotkey = "4";
        }
    }

    /**
     * Returns the key used in the menu to trigger this Action.
     *
     * @return The key we use for this Action in the menu, or null to have it assigned for you
     */
    @Override
    public String hotkey() {
        return hotkey;
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " falls into a valley";
    }
}
