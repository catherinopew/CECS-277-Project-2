
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
        int num = ran.nextInt(4)+1;
        int i = 0;
        while(i != 10){
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
            i++;
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
        int ran = (int)(Math.random() * enemyList.size());
        Random rand = new Random();
        int occurence = rand.nextInt(10)+1; 
        System.out.println(occurence);
        
        if (occurence < 3) {
            Enemy warrior = new Warrior(enemyList.get(ran));            
            return warrior;
        } else if (occurence >= 3 && occurence < 5){
            Enemy warlock = new Warlock(enemyList.get(ran));
            return warlock;
        } else {
            Enemy physical = new Enemy(enemyList.get(ran).getName(), enemyList.get(ran).getMaxHP(), enemyList.get(ran).getItem());
            if (level > 1){
                physical = new Enemy(enemyList.get(ran).getName(), enemyList.get(ran).getMaxHP()+4, enemyList.get(ran).getItem());
             
            }
            return physical;
        } 
    }
}