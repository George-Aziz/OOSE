package model;
import java.util.*;

public class Potion implements Item
{
    private String itemName;
    private int cost;
    private int minEffect;
    private int maxEffect;
    private char effectType;

    public Potion (String itemName, int cost, int minEffect, int maxEffect, char effectType)
    {
       this.itemName = itemName;
       this.cost = cost;
       this.minEffect = minEffect;
       this.maxEffect = maxEffect;
       this.effectType = effectType;
    }

    public Potion(Potion potion)
    {
        itemName = potion.itemName;
        cost = potion.cost;
        minEffect = potion.minEffect;
        maxEffect = potion.maxEffect;
        effectType = potion.effectType;
    }

    public Item clone()
    {
        return new Potion(this);
    }

    @Override public void addToPlayer(Player player) 
    { 
        player.addPotion(this); 
    }

    @Override public void removeFromPlayer(Player player) 
    { 
        player.removePotion(this); 
    }
   
    @Override public String getName() 
    { 
        return this.itemName; 
    }

    @Override public int getCost() 
    { 
        return this.cost; 
    }

    @Override public int getEffect() 
    { 
        Random rand = new Random();
        return rand.nextInt((maxEffect - minEffect) + 1) + minEffect; 
    }

    /* Retrieves type of effect (Heale/Damage) */
    public char getEffectType() 
    { 
        return this.effectType; 
    }

    /* Shop toString */
    @Override public String toShopString()
    {
        String stringType;
        if(effectType == 'D') { stringType = "Damage"; } 
        else { stringType = "Healing"; }

        String stats = itemName + " - [Buy Price: " + cost + "] [Effect: " + minEffect + 
        " - " + maxEffect + "] [Effect Type: " + stringType + "]";
        return stats;
    }

    /* Regular toString */
    @Override public String toString()
    {
        String stringType;
        if(effectType == 'D') { stringType = "Damage"; } 
        else { stringType = "Healing"; }

        return itemName + ":  [Effect Type: " + stringType + " [Effect: " + minEffect + " - " + maxEffect + "]";
    }
}