import java.util.ArrayList;
import java.util.Random;

/** EnemyGenerator class that generates an enemy */
public class EnemyGenerator {
    /** An ArrayList of the enemies */
    private ArrayList <Enemy> enemyList = new ArrayList <Enemy> ();
    /** A randomly generated item */
    private ItemGenerator ig;
    /** A instance of the enemyGenerator */
    private static EnemyGenerator instance = null;

    /** Constructs and adds each enemy to the ArrayList, enemyList */
    private EnemyGenerator() {
        Random ran = new Random();
        int num = 0;
        ig = ItemGenerator.getInstance();
        EnemyFactory f = new EnemyFactory();

        for (int i = 0; i < 10; i++) { //creates a randomized enemy each time
            num = ran.nextInt(4) + 1; 
            enemyList.add(f.createEnemy(num, ig));
        }
    }

    /** getInstance ensures that there is only one instance of enemyGenerator
     * @param ig is used to add to the instance of the enemyGenerator
     * @return the instance of the enemy generator 
     */
    public static EnemyGenerator getInstance() {
        if (instance == null){
            instance = new EnemyGenerator();
        }
        return instance; 
    }

    /** Randomly generates an enemy from the ArrayList
     * and adds stacks to it depending on the level of the game.
     * @param level the level in which the game is on
     * @return Enemy a randomly generated enemy
     */
    public Enemy generateEnemy(int level) {
        Random rand = new Random();
        int ran = (int)(Math.random() * enemyList.size());
        int counter = 2; //health boost
        int occurrence = 0;

        Enemy physical = new Enemy(enemyList.get(ran).getName(), 
        enemyList.get(ran).getMaxHP()+(counter * level), 
        enemyList.get(ran).getItem()); //creates a random base enemy

        if (level > 1) { //stack if level greater than 1
            occurrence = rand.nextInt(2) + 1;
            int stack = 0;

            if (occurrence == 1) { //stack Warrior
                while (stack != level - 1) {
                    physical = new Warrior(physical);
                    stack++;
                }
            }
            else {
                while (stack != level - 1) { //stack Warlock
                    physical = new Warlock(physical);
                    stack++;
                }
            }
        }
        return physical;   
    }
}