import java.util.Random;

/**
 * Froglok is a enemy base class that constructs a Froglok character for the
 * game
 */
public class Froglok extends Enemy {
	/**
	 * Froglok class is a constructor class for Froglok that passes in the
	 * superclass Enemy
	 */
	public Froglok() {
		super("Froglok", 2);
	}

	/**
	 * Attacks the hero for a random amount of damage
	 * 
	 * @param e the hero who is being attacked
	 * @return String the attack message
	 */
	@Override
	public String attack(Entity e) {
		Random rand = new Random();
		int damage = rand.nextInt(5) + 1;
		e.takeDamage(damage);
		return getName() + " attacks " + e.getName() + " for " + damage + " damage.";
	}
}