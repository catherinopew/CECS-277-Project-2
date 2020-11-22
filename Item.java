/** Item class to represent an item */
public class Item {
    /** The name of the item */
    private String name;
    /** The value (in gold) of an item */
    private int value;
    /** The type of an item */
    private char type;

    /** Constructs an item
     * @param n the item's name
     */
    public Item(String n, int v, char t) {
        name = n;
        value = v;
        type = t;
    }

    /** Retrieves the name of the item
     * @return String the item name
     */
    public String getName() {
        return name;
    }
}