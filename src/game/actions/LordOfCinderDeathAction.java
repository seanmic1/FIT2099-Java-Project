package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enemies.LordOfCinder;
import game.items.CinderOfLord;

/**
 * Lord of Cinder Death Action for Lord of Cinder (boss).
 */
public class LordOfCinderDeathAction extends Action {

    /**
     * lordOfCinder: An instance of LordOfCinder
     */
    private LordOfCinder lordOfCinder;

    /**
     * Constructor.
     *
     * @param lordOfCinder An instance of LordOfCinder
     */
    public LordOfCinderDeathAction(LordOfCinder lordOfCinder) {
        this.lordOfCinder = lordOfCinder;
    }

    /**
     * Perform the Action.
     * Here, the boss will be removed from the current map and a CinderOfLord will be added in the current die location.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).addItem(new CinderOfLord(lordOfCinder));
        map.removeActor(actor);
        return """
                ██       ██████  ██████  ██████       ██████  ███████      ██████ ██ ███    ██ ██████  ███████ ██████      ███████  █████  ██      ██      ███████ ███    ██\s
                ██      ██    ██ ██   ██ ██   ██     ██    ██ ██          ██      ██ ████   ██ ██   ██ ██      ██   ██     ██      ██   ██ ██      ██      ██      ████   ██\s
                ██      ██    ██ ██████  ██   ██     ██    ██ █████       ██      ██ ██ ██  ██ ██   ██ █████   ██████      █████   ███████ ██      ██      █████   ██ ██  ██\s
                ██      ██    ██ ██   ██ ██   ██     ██    ██ ██          ██      ██ ██  ██ ██ ██   ██ ██      ██   ██     ██      ██   ██ ██      ██      ██      ██  ██ ██\s
                ███████  ██████  ██   ██ ██████       ██████  ██           ██████ ██ ██   ████ ██████  ███████ ██   ██     ██      ██   ██ ███████ ███████ ███████ ██   ████\s
                """;
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return Null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
