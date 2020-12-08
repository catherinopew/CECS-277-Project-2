/** Enemy class that extends Entity class */
public abstract class Enemy extends Entity {
	/** An enemy's item of type Item */
	private Item item;

	/**
	 * Constructs an enemy
	 * 
	 * @param n   the enemy's name
	 * @param mHp the enemy's max hit points
	 */
	public Enemy(String n, int mHp) {
        super(n, mHp);
        ItemGenerator ig = ItemGenerator.getInstance();
        item = ig.generateItem();
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