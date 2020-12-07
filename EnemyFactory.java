/** EnemyFactory that creates enemies of different types */
public class EnemyFactory {
    /** Creates an enemy depending on the type
     * @param type the type of enemy
     * @param ig the item that the enemy holds onto
     * @return Enemy an enemy
     */
    public Enemy createEnemy(int type, ItemGenerator ig) {
        if (type == 1) {
            return new Troll(ig.generateItem());
        }
        else if (type == 2) {
            return new Froglok(ig.generateItem());
        }
        else if (type == 3) {
            return new Orc(ig.generateItem());
        }
        else if (type == 4) {
            return new Goblin(ig.generateItem());
        }
        return null;
    }
}