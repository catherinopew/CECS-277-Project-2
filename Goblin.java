/** Goblin is a enemy base class that constructs a Goblin character for the game */
public class Goblin extends Enemy{
    /** Goblin class is a constructor class for Goblin that passes in the superclass Enemy
     * @param i is the current item that the Goblin is holding  
     */
    public Goblin(Item i){
        super("Goblin", 2, i);
    }
}