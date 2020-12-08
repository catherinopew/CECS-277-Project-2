import java.util.Random;

/** Warlock is a decorator to each enemy type that uses magical attacks */
public class Warlock extends EnemyDecorator implements Magical {
	/**
	 * Warlock is the constructor that takes in the enemy to get the object of the
	 * superclass
	 * 
	 * @param e is the enemy type that will be passed into Warlock
	 */
	public Warlock(Enemy e) {
		super(e, "Warlock " + e.getName(), e.getMaxHP() + 1);
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
        int random = rand.nextInt(3) + 1;

		if (random == 1) {
            return magicMissile(e) + "\n" + super.attack(e);
		} else if (random == 2) {
            return fireball(e) + "\n" + super.attack(e);
		} else {
            return thunderclap(e) + "\n" + super.attack(e);
        }
	}

	/**
	 * Uses a fireball on the hero
	 * 
	 * @param e the hero being attacked
	 * @return String the attack message
	 */
	@Override
	public String fireball(Entity e) {
		Random rand = new Random();
		int damage = rand.nextInt(7) + 1;
		e.takeDamage(damage);

        return getName().replaceAll("Warlock", "").replaceAll("\\s", "") 
        + " hits " + e.getName() + " with a Fireball for " + damage + " damage.";
	}

	/**
	 * Uses a magic missile on the hero
	 * 
	 * @param e the hero being attacked
	 * @return String the attack message
	 */
	@Override
	public String magicMissile(Entity e) {
		Random rand = new Random();
		int damage = rand.nextInt(7) + 1;
		e.takeDamage(damage);

        return getName().replaceAll("Warlock", "").replaceAll("\\s", "") 
        + " hits " + e.getName() + " with a Magic Missle for " + damage + " damage.";
	}

	/**
	 * Uses thunderclap on the hero
	 * 
	 * @param e the hero being attacked
	 * @return String the attack message
	 */
	@Override
	public String thunderclap(Entity e) {
		Random rand = new Random();
		int damage = rand.nextInt(7) + 1;
		e.takeDamage(damage);

        return getName().replaceAll("Warlock", "").replaceAll("\\s", "") 
        + " zaps " + e.getName() + " with Thunderclap for " + damage + " damage.";
	}
}