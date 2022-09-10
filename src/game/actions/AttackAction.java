package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;
import game.Player;
import game.enemies.Skeleton;
import game.enums.Passives;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * The damage to the target
	 */
	protected int damage;

	/**
	 * The hit rate of the Actor
	 */
	protected int hitRate;

	/**
	 * Zero parameter constructor.
	 */
	public AttackAction() {}

	/**
	 * Constructor.
	 *
	 * @param target The Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
		this.direction = null;
		this.damage = -1;
	}

	/**
	 * Constructor.
	 * 
	 * @param target The Actor to attack
	 * @param direction The direction of the target
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
		this.damage = -1;
	}

	/**
	 * Constructor.
	 *
	 * @param target The Actor to attack
	 * @param direction The direction of the target
	 * @param damage The damage to the target
	 */
	public AttackAction(Actor target, String direction, int damage) {
		this.target = target;
		this.direction = direction;
		this.damage = damage;
	}

	/**
	 * Constructor.
	 *
	 * @param target The Actor to attack
	 * @param direction The direction of the target
	 * @param damage The damage to the target
	 * @param hitRate The hit rate of the actor
	 */
	public AttackAction(Actor target, String direction, int damage, int hitRate) {
		this.target = target;
		this.direction = direction;
		this.damage = damage;
		this.hitRate = hitRate;
	}

	/**
	 * Perform the Action.
	 * Here, if Actor execute this action, we will be checking for the damage from the Actor to the target based on the
	 * Passives skills.
	 * Besides, we will also be checking the live of the Skeleton since it might resurrect from death.
	 *
	 * @param actor The actor performing the action
	 * @param map The map the actor is on
	 * @return A description of what happened that can be displayed to the user
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		int damage;

		// default damage
		if (this.damage < 0) {
			// get weapon damage
			damage = weapon.damage();
			// check for the critical strike
			if (actor.hasCapability(Passives.CRITICAL_STRIKE)) {
				if (rand.nextInt(100) <= 20) {
					damage = damage * 2;
				}
			}
			// check for the critical hit
			else if (actor.hasCapability(Passives.CRITICAL_HIT)) {
				if (rand.nextInt(100) <= 15) {
					damage = damage * 2;
				}
			}

			if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
				return actor + " misses " + target + ".";
			}
		}
		// custom damage
		else {
			damage = this.damage;
		}

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		// if hp is 0
		if (!target.isConscious()) {
			// check if actor is the Skeleton
			if (target instanceof Skeleton) {
				Random rand = new Random();
				if (rand.nextInt(100) <= 50) {
					((Skeleton)target).setLive(((Skeleton)target).getLive()-1);
					target.heal(200);
					result += System.lineSeparator() + target + " is resurrected from death!";
				}
				else {
					((Skeleton)target).setLive(((Skeleton)target).getLive()-2);
				}

				if (((Skeleton)target).getLive() <= 0) {
					map.removeActor(target);
					result += System.lineSeparator() + target + " is killed.";
					target.asSoul().transferSouls(actor.asSoul());
				}
			}
			else {
				if (!(target instanceof Player)) {
					target.asSoul().transferSouls(actor.asSoul());	// transfer souls
				}
			}
		}

		return result;
	}

	/**
	 * Returns a descriptive string.
	 *
	 * @param actor The actor performing the action.
	 * @return The text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
