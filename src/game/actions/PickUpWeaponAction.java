package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;

import java.util.List;

/**
 * Action to allow weapon items to be picked up.
 */
public class PickUpWeaponAction extends PickUpItemAction {

	/**
	 * Constructor.
	 *
	 * @param item The item to pick up
	 */
	public PickUpWeaponAction(Item item) {
		super(item);
	}

	/**
	 * Add the item to the actor's inventory.
	 *
	 * @see Action #execute(Actor, GameMap)
	 * @param actor The actor performing the action
	 * @param map The map the actor is on
	 * @return A suitable description to display in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.locationOf(actor).removeItem(item);
		List<Item> items = List.copyOf(actor.getInventory());
		for (Item item: items) {
			if (item instanceof WeaponItem){
				actor.removeItemFromInventory(item);
			}
		}

		actor.addItemToInventory(item);
		return menuDescription(actor);
	}

	/**
	 * Describe the action in a format suitable for displaying in the menu.
	 *
	 * @see Action #menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return A string that what the actor picked up.
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " picks up the " + item;
	}
}
