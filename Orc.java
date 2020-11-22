import java.util.Random;

/** Orc is a enemy base class that constructs a Orc character for the game */
public class Orc extends Enemy{
    /** Orc class is a constructor class for Orc that passes in the superclass Enemy
     * @param i is the current item that the Orc is holding  
    */
    public Orc(Item i){
        super("Froglok", 9, i);
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
