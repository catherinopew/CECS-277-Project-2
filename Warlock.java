import java.util.Random;

public class Warlock extends Enemy_Decorator {
    public Warlock ( Enemy e ){
        super(e, e.getName() + " Warlock", e.getHP() + 1, e.getItem());
    }
    @Override
    public String attack( Entity e ){
        Random rand = new Random();
        int damage = rand.nextInt(7) + 6;
        e.takeDamage(damage);
        return getName() + " attacks " + e.getName() + " for " + damage + " damage.";
    }
}