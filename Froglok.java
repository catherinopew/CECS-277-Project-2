/** Froglok is a enemy base class that constructs a Froglok character for the game */
public class Froglok extends Enemy {
    /** Froglok class is a constructor class for Froglok that passes in the superclass Enemy
     * @param i is the current item that the Froglok is holding  
     */
    public Froglok(Item i) {
        super("Froglok", 2, i);
    }
}