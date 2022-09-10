package game;

import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 */
public class ResetManager {

    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;
    private List<Resettable> removeList;

    /**
     * Constructor.
     */
    private ResetManager() {
        resettableList = new ArrayList<>();
        removeList = new ArrayList<>();
    }

    /**
     * A singleton reset manager instance.
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager.
     *
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance() {
        if(instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Reset the game by traversing through all the list.
     * By doing this way, it will avoid using `instanceof` all over the place.
     *
     * @param map The current map
     */
    public void run(GameMap map) {
        for (Resettable resettable: resettableList) {
            resettable.resetInstance(map);
            if (!resettable.isExist()) {
                removeList.add(resettable);
            }
        }

        cleanUp();
    }

    /**
     * Add the Resettable instance to the list.
     *
     * @param resettable The interface instance
     */
    public void appendResetInstance(Resettable resettable) {
        resettableList.add(resettable);
    }

    /**
     * Clean up instances (actor, item, or ground) that doesn't exist anymore in the map.
     */
    private void cleanUp() {
        for (Resettable resettable: removeList) {
            resettableList.remove(resettable);
        }
        removeList.clear();
    }
}
