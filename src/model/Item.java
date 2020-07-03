package model;

/* Interface for all items */
public interface Item 
{
    public Item clone();
    public String getName();
    public int getCost();
    public int getEffect();

    public String toString();
    public String toShopString();
    
    public void addToPlayer(Player player);
    public void removeFromPlayer(Player player);
}

