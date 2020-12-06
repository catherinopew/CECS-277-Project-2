import java.util.Random;

/**Factory class is the enemies factory where either enemytypes will be created and warriors/warlocks are created*/
public abstract class Factory {
    /** createEnemy is a class that will be used to generate a enemy in EnemyGenerator Class*/
    public abstract Enemy createEnemy();
    /** Attacks the hero for a random amount of damage
     * @param e the hero who is being attacked
     * @return String the attack message
     */
    public Enemy generateEnemy(int level){
        Enemy newEnemy = createEnemy();
        Random ran = new Random();
        int randomNumber = ran.nextInt(10)+1;
 
        for (int i = 0; i < level; i++){
            randomNumber = ran.nextInt(10)+1;
            if(randomNumber >= 5 && randomNumber <= 7){
                newEnemy = new Warrior(newEnemy);
            } else if(randomNumber >= 9 && randomNumber <=10){
                newEnemy = new Warlock(newEnemy);
            }      
        }
        return newEnemy;
    }
}