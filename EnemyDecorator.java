/** EnemyDecorator is a abstract class that adds onto enemy name and max HP */
public abstract class EnemyDecorator extends Enemy {
	/** Represents an Enemy object */
	private Enemy enemy;
	/** Represents an Item object */
	private Item item;

	/**
	 * EnemyDecorator is the constructor which the name and max HP can be changed
	 * based on the decoration called
	 * 
	 * @param e  the enemy
	 * @param n  the enemy's name
	 * @param hp the enemy's hp
	 * @param i  the enemy's item
	 */
	public EnemyDecorator(Enemy e, String n, int hp, Item i) {
		super(n, hp, i);
		enemy = e;
		item = i;
	}

	/**
	 * The enemy attack method
	 * 
	 * @param ent the entity being attacked
	 * @return an attack message
	 */
	@Override
	public String attack(Entity ent) {
		return enemy.attack(ent);
	}
}