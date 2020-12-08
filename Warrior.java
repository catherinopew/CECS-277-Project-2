import java.util.Random;

/** Warrior class is a decorator to all enemy types giving the enemies */
public class Warrior extends EnemyDecorator {
	/**
	 * Warrior is the constructor that takes in the enemy to get the enemy object of
	 * the superclass
	 * 
	 * @param e is the enemy type that will be passed into Warlock
	 */
	public Warrior(Enemy e) {
		super(e, "Warrior " + e.getName(), e.getMaxHP() + 2);
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
		int damage = rand.nextInt(7) + 1;
		e.takeDamage(damage);
		return getName().replaceAll("Warrior", "").replaceAll("\\s", "") 
		+ " attacks " + e.getName() + " for " + damage + " damage." + "\n" + super.attack(e);
	}
}