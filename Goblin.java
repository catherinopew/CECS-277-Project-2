import java.util.Random;

/** Goblin is a enemy base class that constructs a Goblin character for the game */
public class Goblin extends Enemy{
    /** Goblin class is a constructor class for Goblin that passes in the superclass Enemy
     * @param i is the current item that the Goblin is holding  
     */
    public Goblin(Item i){
        super("Goblin", 2, i);
    }

    /** Attacks the hero for a random amount of damage
     * @param e the hero who is being attacked
     * @return String the attack message
     */
    @Override
    public String attack(Entity e) {
        Random rand = new Random();
        int damage = rand.nextInt(5) + 1;
        e.takeDamage(damage);
        return getName() + " attacks " + e.getName() 
        + " for " + damage + " damage.";
    }
}