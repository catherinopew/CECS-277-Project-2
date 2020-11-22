import java.util.Random;

/** Froglok is a enemy base class that constructs a Froglok character for the game */
public class Froglok extends Enemy{
    /** Froglok class is a constructor class for Froglok that passes in the superclass Enemy
     * @param i is the current item that the Froglok is holding  
    */
    public Froglok(Item i){
        super("Froglok", 6, i);
    }
    /** Attacks the hero for a random amount of damage
     * @param e the hero who is being attacked
     * @return String the attack message
     */
    @Override
    public String attack(Entity e) {
        Random rand = new Random();
        int damage = rand.nextInt(7) + 1;
        e.takeDamage(damage);
        return getName() + " attacks " + e.getName() + " for " + damage + " damage.";
    }
}