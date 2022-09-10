package game.enemies;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.behaviours.RandomDieBehaviour;
import game.behaviours.WanderBehaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * An undead minion.
 */
public class Undead extends Enemy implements Resettable, Soul {

	/** 
	 * Constructor.
	 * All Undead are represented by an 'u' and have 50 hit points.
	 *
	 * @param name The name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50);
		behaviours.add(new RandomDieBehaviour());
		behaviours.add(new WanderBehaviour());
		this.registerInstance();
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * @return A freshly-instantiated IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20,"punches");
	}

	/**
	 * Remove this Undead when the world is reset.
	 *
	 * @param map The current map
	 */
	@Override
	public void resetInstance(GameMap map) {
		map.removeActor(this);
	}

	/**
	 * To state whether this Actor will still be existing when the world is reset.
	 *
	 * @return False
	 */
	@Override
	public boolean isExist() {
		return false;
	}

	/**
	 * Transfer current instance's souls to another Soul instance.
	 *
	 * @param soulObject A target souls
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(50);
	}

	@Override
	public String toString() {
		return this.name + " (" + this.hitPoints + "/" + this.maxHitPoints + ") (no weapon)";
	}
}
