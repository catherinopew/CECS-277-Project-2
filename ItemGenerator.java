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
                itemList.add(new Item(line));
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
        return new Item(itemList.get(random).getName());
    }
}