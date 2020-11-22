public abstract class Enemy_Decorator extends Enemy{ 
    private Enemy enemy;
    private Item item;
    
    public Enemy_Decorator(Enemy e, String n, int hp, Item i){
        super(n,hp,i);
        enemy = e; 
        item = i;
    }
    @Override
    public String attack(Entity ent){
        return enemy.attack(ent);
    }
}
