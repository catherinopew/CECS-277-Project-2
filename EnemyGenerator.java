import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

/** EnemyGenerator class that generates an enemy */
public class EnemyGenerator{
    /** An ArrayList of the enemies */
    private ArrayList <Enemy> enemyList = new ArrayList <Enemy> ();
    /** A randomly generated item */
    private ItemGenerator ig;
    /** A instance of the enemyGenerator */
    private static EnemyGenerator instance = null;

    /** Constructs and adds each enemy to the ArrayList, enemyList
     * @param ig item to be generated 
     */
    public EnemyGenerator(ItemGenerator ig) {
        this.ig = ig;
        Random ran = new Random();
        int num = 0;

        for (int i = 0; i < 10; i++) {
            num = ran.nextInt(4)+1;
            if (num == 1){
                enemyList.add(new Troll(ig.generateItem()));
            } else if (num == 2){
                enemyList.add(new Froglok(ig.generateItem()));
            } else if (num == 3){
                enemyList.add(new Orc(ig.generateItem()));
            } else {
                enemyList.add(new Goblin(ig.generateItem()));
            }
        }
    }

    /** getInstance ensures that there is only one instance of enemyGenerator
    *   @param ig is used to add to the instance of the enemyGenerator
    *   @return the instance of the enemy generator 
    */
    public static EnemyGenerator getInstance(ItemGenerator ig){
        if (instance == null){
            instance = new EnemyGenerator(ig);
        }
        return instance; 
    }

    /** Randomly generates an enemy from the ArrayList
     * @param level the level in which the game is on
     * @return Enemy a randomly generated enemy
     */
    public Enemy generateEnemy(int level) {
        Random rand = new Random();
        int occurrence = 0;
        int ran = (int)(Math.random() * enemyList.size());
        int counter = 2;

        Enemy physical = new Enemy(enemyList.get(ran).getName(), 
        enemyList.get(ran).getMaxHP()+(counter * level), 
        enemyList.get(ran).getItem()); //this gets reset each time

        if (level > 1) {
            occurrence = rand.nextInt(2) + 1;
            int stack = 0;

            if (occurrence == 1) {
                while (stack != level - 1) {
                    physical = new Warrior(physical);
                    stack++;
                }
            }
            else {
                while (stack != level - 1) {
                    physical = new Warlock(physical);
                    stack++;
                }
            }
        }
        return physical;
        /*int ran = (int)(Math.random() * enemyList.size());
        Random rand = new Random();
        int occurence = rand.nextInt(10)+1; 
        System.out.println(occurence);
        int counter = 2;
        
        Enemy physical = new Enemy(enemyList.get(ran).getName(), enemyList.get(ran).getMaxHP()+(counter*level), enemyList.get(ran).getItem());
        
        if (level > 1 || occurence <= 1) {
            Enemy warrior = new Warrior(physical);            
            if (level > 2 || occurence >= 8){
                warrior = new Warrior(warrior);
            } else if (level > 2 || occurence >= 6){
                warrior = new Warlock(warrior);
            }
            return warrior;
        } else if (level > 1 || occurence <= 2 ){
            Enemy warlock = new Warlock(physical);
            if (level > 2 || occurence >= 6){
                warlock = new Warlock(warlock);
            } else if (level > 2 || occurence >= 8){
                warlock = new Warrior(warlock);
            }
            return warlock;
        } else {
            return physical;
        }*/      
    }
}