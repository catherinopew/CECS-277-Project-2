import java.util.Random;

/**
 * A program of a dungeon maze game where you are the hero
 * 
 * @author Catherine Bui and Jacob Sunia
 * @version 12/07/2020
 */
public class Main {
	/**
	 * The main method where you can name your hero and progress through the game
	 */
	public static void main(String[] args) {
		System.out.print("What is your name, traveler? ");
		String name = CheckInput.getString();

		int level = 1; // Start at level 1 and create all necessary objects
		Map map = Map.getInstance();
		map.loadMap(level);
		Hero hero = new Hero(name, map);
		ItemGenerator ig = ItemGenerator.getInstance();
		EnemyGenerator eg = EnemyGenerator.getInstance();

		int choice = 0;
		while (hero.getHP() != 0) { // continue game as long as hero isn't dead
			System.out.println(hero.toString());
			map.displayMap(hero.getLocation());

			System.out.println("1. Go North\n2. Go South\n3. Go East\n" + "4. Go West\n5. Quit"); // display menu
			choice = CheckInput.getIntRange(1, 5);

			if (choice == 1) { // option to move hero North
				hero.goNorth();
			} else if (choice == 2) { // option to move hero South
				hero.goSouth();
			} else if (choice == 3) { // option to move hero East
				hero.goEast();
			} else if (choice == 4) { // option to move hero West
				hero.goWest();
			} else if (choice == 5) { // option to quit
				System.out.println("You've chosen to quit. Game over.");
				break;
			}

            if (monsterRoom(hero, map, eg, level) == true) { // monster room
				Enemy enemy = eg.generateEnemy(level);
				System.out.println("You've encountered a " + enemy.getName());
				while (fight(hero, enemy) == true)
					;
				if (enemy.getHP() == 0) {
					System.out.println("You defeated the " + enemy.getName() + "!");
					System.out.println("It dropped a " + enemy.getItem().getName() + ".");
					map.removeCharAtLoc(hero.getLocation());

					if (hero.getNumItems() == 5) { // give hero option to replace item if bag full
                        System.out.println("Your inventory is full. Do you still want " + 
                        "to pick up this item (Y/N)? ");
						boolean itemChoice = CheckInput.getYesNo();
						if (itemChoice == true) { // ask if user wants to replace an item
                            System.out.println("Which item number would you like to drop? " +
                            "\n" + hero.itemsToString()); // shows current inventory
							int itemNum = CheckInput.getIntRange(1, 5);
							hero.dropItem(itemNum - 1);
							System.out.println("You've chosen to drop an item and replaced it with a "
									+ enemy.getItem().getName() + "."); // display message of what they got
						} else { // otherwise, display message that user chose to not replace anything
							System.out.println("You've chosen to not replace any of " + "your inventory items.");
						}
					}
					if (hero.pickUpItem(enemy.getItem()) == true) {
						System.out.println("You received a " + enemy.getItem().getName() + " from its corpse.");
					}
				}
			} else if (map.getCharAtLoc(hero.getLocation()) == 'i') { // item room
				itemRoom(hero, map, ig);
			} else if (map.getCharAtLoc(hero.getLocation()) == 'n') { // empty room
				System.out.println("There was nothing here.");
			} else if (map.getCharAtLoc(hero.getLocation()) == 's') { // starting point
                System.out.println("You're back at the start. Would you like to" + 
                " visit the store? (Y/N)"); // option for user to visit store
				boolean storeChoice = CheckInput.getYesNo();
				if (storeChoice == true) { // visits store if user picks yes
					store(hero);
				}
			} else if (map.getCharAtLoc(hero.getLocation()) == 'f') { // finishing point
				if (hero.hasKey()) {
					System.out.println("You've completed level " + level + ".");
					level++; // increment level, load a new map, and heal hero to max hp
					map.loadMap(level);
					hero.heal(hero.getMaxHP());
					hero.useKey(); // consumes the key in possession
				} else {
                    System.out.println("You encounter a door, but you don't have the key! " + 
                    "Find it to unlock the next level."); // when user doesn't have the key	
				}
			}
		}
		if (hero.getHP() == 0) { // display game over if hero is dead
			System.out.println("You've died. Game over.");
		}
	}

	/**
	 * Simulates a room with a monster if map character is 'm'
	 * 
	 * @param h     the hero
	 * @param m     the map
	 * @param eg    the generated enemy
	 * @param level the current level of the game
	 * @return boolean true if there is a monster, false otherwise
	 */
	public static boolean monsterRoom(Hero h, Map m, EnemyGenerator eg, int level) {
		boolean hasMonster = false;
		if (m.getCharAtLoc(h.getLocation()) == 'm') {
			hasMonster = true;
		}
		return hasMonster;
	}

	/**
	 * Simulates a fight between the hero and enemy
	 * 
	 * @param h the hero
	 * @param e the enemy
	 * @return boolean true if both hero and enemy are still alive, false otherwise
	 */
	public static boolean fight(Hero h, Enemy e) {
		System.out.println(e.toString());
		int choice = 0;

		if (h.hasPotion() == true) {
			System.out.println("1. Fight\n2. Run Away\n3. Drink Health Potion");
			choice = CheckInput.getIntRange(1, 3);
		} else {
			System.out.println("1. Fight\n2. Run Away");
			choice = CheckInput.getIntRange(1, 2);
		}

		if (choice == 1) { // option to fight
			System.out.println("1. Physical Attack\n2. Magic Attack"); // display attack menu
			choice = CheckInput.getIntRange(1, 2);
			if (choice == 1) {
				System.out.println(h.attack(e));
				if (e.getHP() != 0) { // enemy attacks back as long as it is still alive
					if (h.hasArmorItem() != -1) { // blocks enemy's attack in that turn if you have armor
						System.out.println(e.getName() + " attempts to attack you, "
								+ "but you blocked all of the damage with your armor.");
						h.dropItem(h.hasArmorItem()); // consumes armor once it has been used
					} else {
						System.out.println(e.attack(h));
					}
				}
			} else {
				System.out.println(Magical.MAGIC_MENU); // display magical attack menu
				choice = CheckInput.getIntRange(1, 3);
				if (choice == 1) { // magic missle option
					System.out.println(h.magicMissile(e));
					if (e.getHP() != 0) {
						if (h.hasArmorItem() != -1) {
							System.out.println(e.getName() + " attempts to attack you, "
									+ "but you blocked all of the damage with your armor.");
							h.dropItem(h.hasArmorItem());
						} else {
							System.out.println(e.attack(h));
						}
					}
				} else if (choice == 2) { // fireball option
					System.out.println(h.fireball(e));
					if (e.getHP() != 0) {
						if (h.hasArmorItem() != -1) {
							System.out.println(e.getName() + " attempts to attack you, "
									+ "but you blocked all of the damage with your armor.");
							h.dropItem(h.hasArmorItem());
						} else {
							System.out.println(e.attack(h));
						}
					}
				} else { // thunderclap option
					System.out.println(h.thunderclap(e));
					if (e.getHP() != 0) {
						if (h.hasArmorItem() != -1) {
							System.out.println(e.getName() + " attempts to attack you, "
									+ "but you blocked all of the damage with your armor.");
							h.dropItem(h.hasArmorItem());
						} else {
							System.out.println(e.attack(h));
						}
					}
				}
			}
			if (e.getHP() == 0 || h.getHP() == 0) { // if either enemy or hero dead, end fight
				return false;
			} else { // otherwise, continue fight
				return true;
			}
		} else if (choice == 2) { // option to run away
			Random rand = new Random();
			int random = 0;

			if (h.getLocation().getX() == 0 && h.getLocation().getY() == 0) { // [0, 0]
				random = rand.nextInt(2) + 1; // moves hero in random valid direction
				if (random == 1) {
					h.goSouth();
				} else {
					h.goEast();
				}
			} else if (h.getLocation().getX() == 0 && h.getLocation().getY() == 4) { // [0, 4]
				random = rand.nextInt(2) + 1;
				if (random == 1) {
					h.goSouth();
				} else {
					h.goWest();
				}
			} else if (h.getLocation().getX() == 4 && h.getLocation().getY() == 0) { // [4, 0]
				random = rand.nextInt(2) + 1;
				if (random == 1) {
					h.goNorth();
				} else {
					h.goEast();
				}
			} else if (h.getLocation().getX() == 4 && h.getLocation().getY() == 4) { // [4, 4]
				random = rand.nextInt(2) + 1;
				if (random == 1) {
					h.goNorth();
				} else {
					h.goWest();
				}
			} else if (h.getLocation().getY() == 0) { // first column and in between corners
				random = rand.nextInt(3) + 1;
				if (random == 1) {
					h.goNorth();
				} else if (random == 2) {
					h.goSouth();
				} else {
					h.goEast();
				}
			} else if (h.getLocation().getY() == 4) { // last column and in between corners
				random = rand.nextInt(3) + 1;
				if (random == 1) {
					h.goNorth();
				} else if (random == 2) {
					h.goSouth();
				} else {
					h.goWest();
				}
			} else if (h.getLocation().getX() == 0) { // first row and in between corners
				random = rand.nextInt(3) + 1;
				if (random == 1) {
					h.goSouth();
				} else if (random == 2) {
					h.goWest();
				} else {
					h.goEast();
				}
			} else if (h.getLocation().getX() == 4) { // last row and in between corners
				random = rand.nextInt(3) + 1;
				if (random == 1) {
					h.goNorth();
				} else if (random == 2) {
					h.goWest();
				} else {
					h.goEast();
				}
			} else {
				random = rand.nextInt(4) + 1;
				if (random == 1) {
					h.goNorth();
				} else if (random == 2) {
					h.goSouth();
				} else if (random == 3) {
					h.goEast();
				} else {
					h.goWest();
				}
			}
			return false;
		} else { // option to use health potion
			h.drinkPotion();
			return true;
		}
	}

	/**
	 * Simulates an item room where the user has the option to pick an item up
	 * 
	 * @param h  the hero
	 * @param m  the map
	 * @param ig the generated item
	 */
	public static void itemRoom(Hero h, Map m, ItemGenerator ig) {
		Item item = ig.generateItem();
		System.out.println("You found a " + item.getName() + ".");

		if (h.getNumItems() == 5) {
			System.out.println("Your inventory is full. Do you still want " + "to pick up this item (Y/N)? ");
			boolean itemChoice = CheckInput.getYesNo();
			if (itemChoice == true) { // ask if user wants to replace an item
                System.out.println("Which item number would you like to drop? " + 
                "\n" + h.itemsToString()); // show the current inventory
				int itemNum = CheckInput.getIntRange(1, 5);
                System.out.println("You've chosen to drop an item and replaced it with a "
                + item.getName() + "."); // display message of what they got
				h.dropItem(itemNum - 1);
			} else { // otherwise, display message that user chose to not replace anything
				System.out.println("You've chosen to not replace any of " + "your inventory items.");
			}
		}
		if (h.pickUpItem(item) == true) {
			System.out.println("You've picked up a " + item.getName() + ".");
			m.removeCharAtLoc(h.getLocation());
		}
	}

	/**
	 * Simulates a store where the hero can buy items or sell items
	 * 
	 * @param h the hero
	 */
	public static void store(Hero h) {
		System.out.println("Welcome to the store!");
		int choice = 0;

		while (choice != 3) { // display store menus until user quits
			System.out.println("What would you like to do?");
			System.out.println("1. Buy an Item\n2. Sell an Item\n3. Quit");
			choice = CheckInput.getIntRange(1, 3);

			if (choice == 1) {
				System.out.println("Which item would you like to buy?");
				System.out.println("1. Key - 50 Gold\n2. Health Potion - 25 Gold");
				int buyChoice = CheckInput.getIntRange(1, 2);
				ItemGenerator ig = ItemGenerator.getInstance();
				if (buyChoice == 1) { // option to buy a key
					Item item = ig.getKey(); // retrieves a key
					if (h.getGold() < item.getValue()) {
						System.out.println("You cannot afford this item!");
					} else if (h.getNumItems() == 5) { // make user sell something if bag full
                        System.out.println("Your inventory is full! " + 
                        "You cannot buy this item. Please sell something.");
					} else { // successfully bought message
						h.spendGold(item.getValue());
						h.pickUpItem(item);
						System.out.println("You've bought a Key and put it in your bag.");
					}
				} else if (buyChoice == 2) { // option to buy a potion
					Item item = ig.getPotion(); // retrieves a potion
					if (h.getGold() < item.getValue()) {
						System.out.println("You cannot afford this item!");
					} else if (h.getNumItems() == 5) { // make user sell something if bag full
                        System.out.println("Your inventory is full!" + 
                        "You cannot buy this item. Please sell something.");
					} else { // successfully bought message
						h.spendGold(item.getValue());
						h.pickUpItem(item);
						System.out.println("You've bought a Health Potion " + "and put it in your bag.");
					}
				}
			} else if (choice == 2) { // option to sell an item
				if (h.getNumItems() > 0) { // can sell item as long as there's something in bag
                    System.out.println("Which item number would you like to sell? " + 
                    "\n" + h.itemsToString()); // shows current inventory
					int itemNum = CheckInput.getIntRange(1, h.getNumItems());
					Item item = h.dropItem(itemNum - 1);
					h.collectGold(item.getValue()); // drops item, collects gold, and displays success message
                    System.out.println("You've chosen to sell a " + item.getName() + 
                    " and received " + item.getValue() + " gold.");
				} else { // inventory empty message
					System.out.println("There is nothing to sell!");
				}
			} else if (choice == 3) { // option to leave
				System.out.println("Thank you and have a nice day!");
			}
		}
	}
}