import java.util.Random;

public class Warlock extends Enemy_Decorator implements Magical{
    public Warlock ( Enemy e ){
        super(e, "Warlock " + e.getName(), e.getMaxHP() + 1, e.getItem());
    }
    @Override
    public String attack( Entity e ){
        Random rand = new Random();
        int random = rand.nextInt(3) + 1;
        if (random == 1) {
            return magicMissile(e);
        }
        else if (random == 2) {
            return fireball(e);
        }
        else {
            return thunderclap(e);
        }
    }
    
    /** Uses a fireball on the hero
     * @param e the hero being attacked
     * @return String the attack message
     */
    @Override
    public String fireball(Entity e) {
        Random rand = new Random();
        int damage = rand.nextInt(7) + 1;
        e.takeDamage(damage);

        return getName() + " hits " + e.getName() + " with a Fireball for "
        + damage + " damage.";
    }
    
    /** Uses a magic missile on the hero
     * @param e the hero being attacked
     * @return String the attack message
     */
    @Override
    public String magicMissile(Entity e) {
        Random rand = new Random();
        int damage = rand.nextInt(7) + 1;
        e.takeDamage(damage);

        return getName() + " hits " + e.getName() + " with a Magic Missle for "
        + damage + " damage.";
    }
    /** Uses thunderclap on the hero
     * @param e the hero being attacked
     * @return String the attack message
     */
    @Override
    public String thunderclap(Entity e) {
        Random rand = new Random();
        int damage = rand.nextInt(7) + 1;
        e.takeDamage(damage);

        return getName() + " zaps " + e.getName() + " with Thunderclap for "
        + damage + " damage.";
    }
}