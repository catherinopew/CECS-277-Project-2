import java.util.*;

/** EnemyFactory that creates enemies of different types */
public class EnemyFactory {

    /** 
     * Creates a random enemy
     * 
     * @param ig a randomly generated item
     * @return an enemy that has been created
     */
	public Enemy createEnemy(ItemGenerator ig) {
		Random ran = new Random();
        int type = ran.nextInt(4) + 1;

		if (type == 1) { // creates a random enemy
			return new Troll();
		} else if (type == 2) {
			return new Froglok();
		} else if (type == 3) {
			return new Orc();
		} else if (type == 4) {
			return new Goblin();
		}
		return null;
    }
}