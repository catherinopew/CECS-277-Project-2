import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** ItemGenerator class that generates an item */
public class ItemGenerator {
    /** An ArrayList of the items */
    private ArrayList <Item> itemList = new ArrayList <Item> ();
    /** The instance of the class*/
    private static ItemGenerator instance = null;

    /** Reads from the ItemList.txt file 
     * and adds each item to the ArrayList, itemList
     */
    public ItemGenerator() {
        try {
            Scanner read = new Scanner(new File("ItemList.txt"));
            while(read.hasNextLine()) {
                String line = read.nextLine();
                String [] token = line.split(",");
                itemList.add(new Item(token[0], Integer.parseInt(token[1]),
                token[2].charAt(0)));
            }
            read.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("File Not Found - place in project folder");
        }
    }

     /** Checks for the instance of the ItemGenerator and making sure that 
      * the object has not already been created
      */
    public static ItemGenerator getInstance(){
        if (instance == null){
            instance = new ItemGenerator();
        }
        return instance; 
    }

    /** Randomly generates an item from the ArrayList
     * @return a randomly generated item
     */
    public Item generateItem() {
        int random = (int)(Math.random() * itemList.size());
        String itemName = itemList.get(random).getName();
        int itemValue = itemList.get(random).getValue();
        char itemType = itemList.get(random).getType();
        return new Item(itemName, itemValue, itemType);
    }

    public Item getPotion() {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getType() == 'p') {
                return itemList.get(i);
            }
        }
    }

    public Item getKey() {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getType() == 'k') {
                return itemList.get(i);
            }
        }
    }
}