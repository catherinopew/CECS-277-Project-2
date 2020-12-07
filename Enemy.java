/** Enemy class that extends Entity class */
public abstract class Enemy extends Entity {
	/** An enemy's item of type Item */
	private Item item;

	/**
	 * Constructs an enemy
	 * 
	 * @param n   the enemy's name
	 * @param mHp the enemy's max hit points
	 * @param i   the enemy's item
	 */
	public Enemy(String n, int mHp, Item i) {
		super(n, mHp);
		item = i;
	}

	/**
	 * Retrieves an item
	 * 
	 * @return the enemy's item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Attacks the hero for a random amount of damage
	 * 
	 * @param e the hero who is being attacked
	 * @return String the attack message
	 */
	public abstract String attack(Entity e);
}