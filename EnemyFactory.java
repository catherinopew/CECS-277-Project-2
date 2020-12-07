import java.util.*;

/** EnemyFactory that creates enemies of different types */
public abstract class EnemyFactory {
	/**
	 * Creates an enemy depending on the type
	 * 
	 * @param level is the level of the game
	 * @return Enemy an enemy or decorated enemy based on level
	 */
	public Enemy generateEnemy(int level) {
		Random rand = new Random();
		int counter = 2; // health boost
		int occurrence = 0;
		Enemy physical = createEnemy();

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
	/**
	 * Creates an enemy depending on the type
	 */
	public abstract Enemy createEnemy();
}