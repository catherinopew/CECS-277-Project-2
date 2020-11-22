import java.util.Random;

public class Troll extends Enemy{
    // instance variables - replace the example below with your own
    public Troll(Item i){
        super("Troll", 7, i);
    }
    @Override
    public String attack(Entity e) {
        Random rand = new Random();
        int damage = rand.nextInt(7) + 1;
        e.takeDamage(damage);
        return getName() + " attacks " + e.getName() + " for " + damage + " damage.";
    }
}
