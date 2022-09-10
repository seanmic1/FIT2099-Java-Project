package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

import java.util.List;

/**
 * Purchase Action for PLayer.
 */
public abstract class PurchaseAction extends Action {

    /**
     * Used to check if the Player has enough souls to purchase.
     *
     * @param actor The actor that perform this method
     * @param souls The number of souls that the actor have currently
     * @return True if Player has sufficient souls; False otherwise
     */
    public boolean checkSouls(Actor actor, int souls) {
        boolean valid;
        // check for player's soul, then deduct if enough
        if (actor.asSoul().getSouls() >= souls) {
            actor.asSoul().subtractSouls(souls);
            valid = true;
        }
        else {
            valid = false;
        }

        return valid;
    }

    /**
     * Used to get the number of souls that the Player still need to buy an item if the soul is not enough
     *
     * @param actor The actor that perform this method
     * @param souls The number of souls that the actor have currently
     * @return The number of souls that the Player still need to buy an item
     */
    public int countDeficit(Actor actor, int souls) {
        return souls - actor.asSoul().getSouls();
    }

    /**
     * Removes the current weapon item from inventory.
     *
     * @param actor The actor that need to remove the current weapon
     */
    public void removeWeapon(Actor actor) {
        List<Item> inventory = List.copyOf(actor.getInventory());
        for (Item item: inventory) {
            if (item instanceof WeaponItem) {
                actor.removeItemFromInventory(item);
            }
        }
    }
}
