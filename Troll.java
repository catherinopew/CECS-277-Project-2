import java.util.Random;

/**
 * Troll is a enemy base class that constructs a Troll character for the game
 */
public class Troll extends Enemy {
	/**
	 * Troll class is a constructor class for Troll that passes in the superclass
	 * Enemy
	 */
	public Troll() {
		super("Troll", 5);
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