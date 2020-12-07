import java.util.ArrayList;
import java.util.Random;

/** EnemyGenerator class that generates an enemy */
public class EnemyGenerator extends EnemyFactory {
	/** A randomly generated item */
	private ItemGenerator ig;
	/** A instance of the enemyGenerator */
	private static EnemyGenerator instance = null;

	/** Constructs and adds each enemy to the ArrayList, enemyList */
	@Override
	public Enemy createEnemy() {
		Random ran = new Random();
		int type = ran.nextInt(4) + 1;
		if (type == 1) {
			return new Troll(ig.getInstance().generateItem());
		} else if (type == 2) {
			return new Froglok(ig.getInstance().generateItem());
		} else if (type == 3) {
			return new Orc(ig.getInstance().generateItem());
		} else if (type == 4) {
			return new Goblin(ig.getInstance().generateItem());
		}
		return new Goblin(ig.getInstance().generateItem());
	}

	/**
	 * getInstance gets the instance of the enemy
	 * 
	 * @return the instance of the enemy
	 */
	public static EnemyGenerator getInstance() {
		if (instance == null) {
			instance = new EnemyGenerator();
		}
		return instance;
	}
}