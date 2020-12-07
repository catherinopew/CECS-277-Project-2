/** Item class to represent an item */
public class Item implements Prototype {
	/** The name of the item */
	private String name;
	/** The value (in gold) of an item */
	private int value;
	/** The type of an item */
	private char type;

	/**
	 * Constructs an item
	 * 
	 * @param n the item's name
	 */
	public Item(String n, int v, char t) {
		name = n;
		value = v;
		type = t;
	}

	/** Constructor copy for prototype */
	public Item(Item i) {
		if (i != null) {
			name = i.getName();
			value = i.getValue();
			type = i.getType();
		}
	}

	/**
	 * Retrieves the name of the item
	 * 
	 * @return String the item name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the value of the item
	 * 
	 * @return int item's monetary value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Retrieves the item's type
	 * 
	 * @return the item type
	 */
	public char getType() {
		return type;
	}

	/** Creates a deep copy */
	@Override
	public Prototype clone() {
		return new Item(this);
	}
}