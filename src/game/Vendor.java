package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.actions.BuyMenuAction;
import game.actions.BuyWeaponAction;
import game.actions.ExitBuyMenuAction;
import game.actions.IncreaseHPAction;
import game.actions.TradeCinderOfLordAction;
import game.enums.Abilities;
import game.items.CinderOfLord;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;
import game.weapons.StormRuler;

/**
 * Class representing vendor in the game.
 */
public class Vendor extends Actor {

    /**
     * Constructor.
     *
     * @param name        The name of the Vendor
     * @param displayChar The character that will represent the Vendor in the display
     * @param hitPoints   The Vendor's starting hit points
     */
    public Vendor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Abilities.CANNOT_HOLD_WEAPON);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions Collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn
     * @param map The map containing the Actor
     * @param display The I/O object to which messages may be written
     * @return Action to perform on the current turn
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns a collection of the Buy and Trade Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction String representing the direction of the other Actor
     * @param map Current GameMap
     * @return A collection of Buy and Trade Actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (otherActor.hasCapability(Abilities.BUY)) {
            actions.add(new IncreaseHPAction());
            actions.add(new BuyWeaponAction(new Broadsword(),500));
            actions.add(new BuyWeaponAction(new GiantAxe(),1000));
            actions.add(new BuyWeaponAction(new StormRuler(),2000));
            for (Item item: otherActor.getInventory()) {
                if (item instanceof CinderOfLord) {
                    actions.add(new TradeCinderOfLordAction((CinderOfLord) item));
                }
            }
            actions.add(new ExitBuyMenuAction());
        }
        else if (otherActor.hasCapability(Abilities.MOVE)) {
            actions.add(new BuyMenuAction());
        }

        return actions;
    }
}
