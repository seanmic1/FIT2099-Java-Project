package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Menu;
import game.actions.AttackAction;
import game.actions.PurchaseAction;
import game.actions.KillPlayerAction;
import game.behaviours.RangeAttackBehaviour;
import game.enemies.Enemy;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.items.EstusFlask;
import game.weapons.Broadsword;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	/**
	 * menu: The menu action that a Player can do
	 * souls: The number of souls that a Player has
	 * spawnPoint: The location that the Player should spawn when the world is reset
	 * gameMaps: An arrayList of gameMaps that the Player can travel
	 */
	private final Menu menu = new Menu();
	private int souls;
	private Location spawnPoint;
	private ArrayList<GameMap> gameMaps;

	/**
	 * Constructor.
	 *
	 * @param name Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints Player's starting number of hitPoints
	 * @param spawnPoint The location of the Player
	 */
	public Player(String name, char displayChar, int hitPoints, Location spawnPoint) {
		super(name, displayChar, hitPoints);
		this.souls = 0;
		this.spawnPoint = spawnPoint;
		this.gameMaps = new ArrayList<>();
		this.addItemToInventory(new Broadsword());
		this.addItemToInventory(new EstusFlask(this));
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.MOVE);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.CAN_ENTER_FLOOR_FOGDOOR);
		this.addCapability(Abilities.CAN_ENTER_VALLEY);
		this.addCapability(Abilities.CANNOT_ENTER_CEMETERY);
		this.addCapability(Abilities.ATTACK);
		this.addCapability(Abilities.CHARGE);
		this.addCapability(Abilities.TELEPORT);
		this.registerInstance();
	}

	/**
	 * Select and return an action to perform on the current turn.
	 * Here, we do some checking of Abilities of the Player has and depends on the conditions, some actions will be
	 * added and some will be removed.
	 *
	 * @param actions Collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn
	 * @param map The map containing the Actor
	 * @param display The I/O object to which messages may be written
	 * @return Action to perform on the current turn
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (!this.isConscious()) {
			return new KillPlayerAction();
		}

		// action that the Player can do when he's holding Darkmoon LongBow
		if (this.hasCapability(Abilities.RANGED_ATTACK)) {
			Set<Location> locations = new HashSet<>();
			Set<Location> noCheckLocations = new HashSet<>();
			Location actorLocation = map.locationOf(this);
			for (Exit exit : actorLocation.getExits()) {
				noCheckLocations.add(exit.getDestination());
				for (Exit exit2 : exit.getDestination().getExits()) {
					for (Exit exit3 : exit2.getDestination().getExits())
						locations.add(exit3.getDestination());
				}
			}
			locations.removeAll(noCheckLocations);
			for (Location location : locations) {
				if (location.containsAnActor()) {
					if (location.getActor() instanceof Enemy) {
						actions.add(new RangeAttackBehaviour(location.getActor(), location));
					}
				}
			}
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}

		Actions removeActions = new Actions();

		// if the player doesn't have ATTACK capability, remove all AttackActions
		if (!this.hasCapability(Abilities.ATTACK)) {
			for (Action action : actions) {
				if (action instanceof AttackAction) {
					removeActions.add(action);
				}
			}
		}

		// If Player doesn't have MOVE capability, then remove every MoveActorAction from Player
		if (!this.hasCapability(Abilities.MOVE)) {
			for (Action action : actions) {
				if (!(action instanceof PurchaseAction)) {
					removeActions.add(action);
				}
			}
		}

		// Remove all actions that are to be removed
		for (Action action: removeActions) {
			actions.remove(action);
		}

		display.println(this.name + " (" + this.hitPoints + "/" + this.maxHitPoints + "), holding " + this.getWeapon()
						+ ", " + this.souls + " souls");

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Transfer current instance's souls to another Soul instance.
	 *
	 * @param soulObject A target souls
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(this.souls);
		this.souls = 0;
	}

	/**
	 * Increase souls to current instance's souls.
	 *
	 * @param souls The number of souls to be incremented
	 * @return Transaction status. True if addition successful, otherwise False
	 */
	@Override
	public boolean addSouls(int souls) {
		this.souls += souls;
		return true;
	}

	/**
	 * Allow other classes to deduct the number of this instance's souls.
	 *
	 * @param souls The number souls to be deducted
	 * @return Transaction status. True if subtraction successful, otherwise False.
	 */
	@Override
	public boolean subtractSouls(int souls) {
		boolean status = false;
		if (this.souls >= souls) {
			this.souls -= souls;
			status = true;
		}
		return status;
	}

	/**
	 * Getter of souls.
	 *
	 * @return The number of souls
	 */
	@Override
	public int getSouls() {
		return this.souls;
	}

	/**
	 * Reset everything of this Player when the world is reset.
	 *
	 * @param map The current map
	 */
	@Override
	public void resetInstance(GameMap map) {
		this.hitPoints = this.maxHitPoints;
		map.moveActor(this, spawnPoint);
	}

	/**
	 * To state whether this Actor will still be existing when the world is reset.
	 *
	 * @return The existence of the instance in the game.
	 */
	@Override
	public boolean isExist() {
		return true;
	}

	/**
	 * Setter of spawnPoint.
	 *
	 * @param spawnPoint The location of the spawn point of the Player
	 */
	public void setSpawnPoint(Location spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	/**
	 * Getter of gameMaps.
	 *
	 * @return An arrayList of GameMap available
	 */
	public ArrayList<GameMap> getGameMaps() {
		return gameMaps;
	}

	/**
	 * Setter of gameMaps.
	 *
	 * @param gameMap An instance of GameMap
	 */
	public void setGameMaps(GameMap gameMap) {
		this.gameMaps.add(gameMap);
	}

	/**
	 * Getter of maxHitPoints.
	 *
	 * @return The maximum hit points of the PLayer
	 */
	public int getMaxHitPoints() {
		return maxHitPoints;
	}
}
