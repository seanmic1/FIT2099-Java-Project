package game.weapons;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.PickUpWeaponAction;
import game.actions.ChargeAction;
import game.actions.WindSlashAction;
import game.enemies.YhormTheGiant;
import game.enums.Abilities;
import game.enums.Passives;
import game.interfaces.Chargeable;

import java.util.List;

/**
 * A class that represent StormRuler weapon in the game.
 */
public class StormRuler extends WeaponItem implements Chargeable {

    /**
     * CHARGE_MAX: The maximum number of charge that needed to use the active skill
     * chargeCount: To record down the number of charge that has been charged by the Actor
     * charging: To state whether the StormRuler is in charging status or not
     */
    private final int CHARGE_MAX = 3;
    private int chargeCount;
    private boolean charging;

    /**
     * Constructor.
     */
    public StormRuler() {
        super("Storm Ruler",'7',70,"slashes",60);
        this.portable = false;
        this.chargeCount = 0;
        this.charging = false;
    }

    /**
     * Add passives skill of CRITICAL_STRIKE to Actor's capability if they are holding it.
     * Add allowableActions based on the condition of the location of Player and YhormTheGiant because WindSlashAction
     * can only be used to attack YhormTheGiant.
     * This method is called once per turn, if the Item is being carried.
     *
     * @param currentLocation The location of the actor carrying this Item
     * @param actor The actor carrying this Item
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (!actor.hasCapability(Passives.CRITICAL_STRIKE)) {
            actor.addCapability(Passives.CRITICAL_STRIKE);
        }

        allowableActions = new Actions();
        if (chargeCount == CHARGE_MAX) {
            charging = false;
            List<Exit> exits = currentLocation.getExits();
            for (Exit exit: exits) {
                if (exit.getDestination().containsAnActor() && exit.getDestination().getActor() instanceof YhormTheGiant) {
                        allowableActions = new Actions(new WindSlashAction(this, exit.getName(),
                                                                            exit.getDestination().getActor()));
                }
            }

        }
        else {
            allowableActions = new Actions(new ChargeAction(this));
        }

        if (charging) {
            actor.removeCapability(Abilities.ATTACK);
        }
        else {
            if (!(actor.hasCapability(Abilities.ATTACK))) {
                actor.addCapability(Abilities.ATTACK);
            }
        }
    }

    /**
     * Create and return an action to pick this Item up.
     *
     * @param actor An actor that will interact with this item
     * @return A new PickUpItemAction if this Item is portable, null otherwise
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpWeaponAction(this);
    }

    /**
     * Increase the number of charge.
     */
    public void charge() {
        if (chargeCount == 0) {
            charging = true;
        }
        if (chargeCount < CHARGE_MAX) {
            chargeCount += 1;
        }
    }

    /**
     * Reset the charge count of StormRuler.
     */
    @Override
    public void resetCharge() {
        chargeCount = 0;
    }

    /**
     * Getter of chargeCount.
     *
     * @return The number of times of chargeCount
     */
    @Override
    public int getCharges() {
        return chargeCount;
    }

    /**
     * Getter of CHARGE_MAX.
     *
     * @return The maximum charge charge count of StormRuler.
     */
    @Override
    public int getMaxCharges() {
        return CHARGE_MAX;
    }
}
