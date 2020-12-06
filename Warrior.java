import java.util.Random;
/** Warrior class is a decorator to all enemy types giving the enemies*/
public class Warrior extends Enemy_Decorator {
    /** Warrior is the constructor that takes in the enemy to get the enemy object
     *  of the superclass
     *  @param e is the enemy type that will be passed into Warlock
    */
    public Warrior ( Enemy e ){
        super(e, " Warlock " + e.getName(), e.getMaxHP() + 1, e.getItem());
    }
    /** Attacks the hero for a random amount of damage
     * @param e the hero who is being attacked
     * @return String the attack message
     */
    @Override
    public String attack( Entity e ){
        Random rand = new Random();
        int damage = rand.nextInt(7) + 4;
        e.takeDamage(damage);
        return getName() + " attacks " + e.getName() + " for " + damage + " damage.";
    }
}