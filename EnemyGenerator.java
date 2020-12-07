import java.util.Random;

/** EnemyGenerator class that generates an enemy */
public class EnemyGenerator extends EnemyFactory {
	/** A randomly generated item */
	private ItemGenerator ig;
	/** A instance of the enemyGenerator */
    private static EnemyGenerator instance = null;
    
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

    /**
	 * Creates an enemy depending on the type
	 * 
	 * @param level is the level of the game
	 * @return Enemy an enemy or decorated enemy based on level
	 */
	public Enemy generateEnemy(int level) {
		Random rand = new Random();
        int occurrence = 0;
        ig = ItemGenerator.getInstance();
		Enemy physical = createEnemy(ig);

		if (level > 1) { // stack if level greater than 1
			occurrence = rand.nextInt(2) + 1;
			int stack = 0;

			if (occurrence == 1) { // stack Warrior
				while (stack != level - 1) {
					physical = new Warrior(physical);
					stack++;
				}
			} else {
				while (stack != level - 1) { // stack Warlock
					physical = new Warlock(physical);
					stack++;
				}
			}
		}
		return physical;
    }
}