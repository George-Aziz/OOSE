package model;

import java.util.*;

public class Armour implements Item 
{
    private String itemName;
    private int cost;
    private int minDefence;
    private int maxDefence;
    private String material;

    public Armour() //Creation of shop to find lowest priced armour
    { 
        cost = Integer.MAX_VALUE; 
    }

    public Armour(String itemName, int cost, int minDefence, int maxDefence, String material) 
    {
        this.itemName = itemName;
        this.cost = cost;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
        this.material = material;
    }

    public Armour(Armour armour) //Copy Constructor to be able to clone
    {
        itemName = armour.itemName;
        cost = armour.cost;
        minDefence = armour.minDefence;
        maxDefence = armour.maxDefence;
        material = armour.material;
    }

    public Item clone() //To clone armour in the shop
    {
        return new Armour(this);
    }

    @Override public void addToPlayer(Player player)
    { 
        player.addArmour(this); 
    }

    @Override public void removeFromPlayer(Player player) 
    { 
        player.removeArmour(this); 
    }
    
    @Override public String getName() 
    { 
        return itemName; 
    }

    @Override public int getCost() 
    { 
        return cost; 
    }

    @Override public int getEffect() 
    {
        Random rand = new Random();
        return rand.nextInt((maxDefence - minDefence) + 1) + minDefence; 
    }

    /* Shop toString */
    @Override public String toShopString()
    {
        String stats = itemName + ": [Buy Price: " + cost + "] [Defence: " + minDefence + " - " + 
        maxDefence + "] [Material: " + material + "]";

        return stats;
    }

    /* Regular toString */
    @Override public String toString()
    {
        return itemName + ":  [Defence: " + minDefence + " - " + maxDefence + "]";
    }
}