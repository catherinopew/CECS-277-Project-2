/** Troll is a enemy base class that constructs a Troll character for the game */
public class Troll extends Enemy{
    /** Troll class is a constructor class for Troll that passes in the superclass Enemy
     * @param i is the current item that the Troll is holding  
     */
    public Troll(Item i){
        super("Troll", 5, i);
    }
}