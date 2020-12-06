import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

/** EnemyGenerator class that generates an enemy */
public class EnemyGenerator extends Factory {
    /** A randomly generated item */
    private ItemGenerator ig;
    /** A instance of the enemyGenerator */
    private static EnemyGenerator instance = null;

    @Override
    public Enemy createEnemy(){
        Random ran = new Random();
        int randomNumber = ran.nextInt(4)+1;
        
        if (randomNumber == 0){
            return new Froglok(ig.getInstance().generateItem());
        } else if (randomNumber == 0){
            return new Orc(ig.getInstance().generateItem());
        } else if (randomNumber == 0){
            return new Troll(ig.getInstance().generateItem());
        } else if (randomNumber == 0){
            return new Goblin(ig.getInstance().generateItem());
        }
        return new Goblin(ig.getInstance().generateItem());
    }
    /** getInstance ensures that there is only one instance of enemyGenerator
    *   @param ig is used to add to the instance of the enemyGenerator
    *   @return the instance of the enemy generator 
    */
    public static EnemyGenerator getInstance(ItemGenerator ig){
        if (instance == null){
            instance = new EnemyGenerator();
        }
        return instance; 
    }
}