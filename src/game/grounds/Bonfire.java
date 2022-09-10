package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.ActivateBonfireAction;
import game.actions.BonfireTeleportAction;
import game.actions.UseBonfireAction;
import game.enums.Abilities;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represent Bonfire in the game.
 */
public class Bonfire extends Ground {

    /**
     * name: The name of the bonfire
     * location: The location of the bonfire
     * isLit: To check whether the bonfire is activated or not
     * teleportBonfire: An arrayList of bonfire that the Player can teleport to
     */
    private String name;
    private Location location;
    private boolean isLit;
    private List<Bonfire> teleportBonfires;

    /**
     * Constructor.
     *
     * @param name The name of the bonfire
     * @param location The location of the bonfire
     */
    public Bonfire(String name, Location location) {
        super('B');
        this.name = name;
        this.location = location;
        this.teleportBonfires = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param name The name of the bonfire
     * @param location The location of the bonfire
     * @param lit To check whether the bonfire is activated or not
     */
    public Bonfire(String name, Location location, boolean lit) {
        super('B');
        this.name = name;
        this.location = location;
        this.isLit = lit;
        this.teleportBonfires = new ArrayList<>();
    }

    /**
     * Returns true where every Actor can enter this location.
     * Actors can enter a location if the bonfire is passable and there isn't an Actor there already.
     *
     * @param actor The Actor who might be moving
     * @return True if the Actor can enter this location
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }

    /**
     * Returns an Action list of the Player can choose to do when Player is interacting with bonfire.
     *
     * @param actor The Actor acting these actions
     * @param location The current Location of the Actor
     * @param direction The direction of the Ground from the Actor
     * @return A new collection of Actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();

        // this if statement allow actor to only get allowable actions when it stands on top of bonfire
        if (location.map().locationOf(actor).getGround() instanceof Bonfire) {
            if (!isLit) {
                actions.add(new ActivateBonfireAction(this));
            }

            if (isLit && actor.hasCapability(Abilities.REST)) {
                actions.add(new UseBonfireAction(this));
            }

            if (isLit && actor.hasCapability(Abilities.TELEPORT)) {
                for (Bonfire bonfires: teleportBonfires) {
                    if (bonfires.isLit() && !(bonfires.equals(this))) {
                        actions.add(new BonfireTeleportAction(bonfires));
                    }
                }
            }
        }

        return actions;
    }

    /**
     * Return True if the bonfire is activated; otherwise False.
     *
     * @return True if the bonfire is activated; otherwise False
     */
    private boolean isLit() {
        return isLit;
    }

    /**
     * Getter of location.
     *
     * @return The location of the bonfire
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Method that add other bonfires to the teleportBonfires so Player can choose from the arrayList where they can
     * teleport to.
     *
     * @param bonfire The instance of other bonfire
     */
    public void addOtherBonfire(Bonfire bonfire) {
        teleportBonfires.add(bonfire);
    }

    /**
     * Getter of name.
     *
     * @return The name of the bonfire
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of isLit.
     */
    public void setIsLit() {
        this.isLit = true;
    }
}
