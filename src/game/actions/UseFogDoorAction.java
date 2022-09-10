package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
import game.Player;

import java.util.ArrayList;

/**
 * Use Fog Door Action for the Player to move to another map.
 */
public class UseFogDoorAction extends Action {

    /**
     * Perform the Action.
     * Here, Player will be moved to the bonfire that chosen from menu.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return A description of what happened that can be displayed to the user
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";
        MoveActorAction moveAction;
        ArrayList<GameMap> gameMapArray = ((Player)actor).getGameMaps();

        if (map.equals(gameMapArray.get(0))) {
            moveAction = new MoveActorAction(gameMapArray.get(1).at(36, 0),"to Anor Londo");
            ret = moveAction.execute(actor, gameMapArray.get(1));

        }
        else if (map.equals(gameMapArray.get(1))) {
            moveAction = new MoveActorAction(gameMapArray.get(0).at(42, 25),"to Profane Capital");
            ret = moveAction.execute(actor, gameMapArray.get(0));
        }

        return ret;
    }

    /**
     * Returns a descriptive string.
     *
     * @param actor The actor performing the action
     * @return The text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        String ret = "";
        ArrayList<GameMap> gameMapArray = ((Player)actor).getGameMaps();
        if (gameMapArray.get(0).contains(actor)) {
            ret = actor + " moves to Anor Londo";
        }
        else {
            ret = actor + " moves to Profane Capital";
        }
        return ret;
    }
}
