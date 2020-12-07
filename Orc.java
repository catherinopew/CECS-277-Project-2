/** Orc is a enemy base class that constructs a Orc character for the game */
public class Orc extends Enemy{
    /** Orc class is a constructor class for Orc that passes in the superclass Enemy
     * @param i is the current item that the Orc is holding  
     */
    public Orc(Item i) {
        super("Orc", 4, i);
    }
}