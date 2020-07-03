package model;
import java.util.*;
public class BaseWeapon extends Weapon 
{
    private String itemName;
    private int cost;
    private int minDamage;
    private int maxDamage;
    private String damageType;
    private String weaponType;

    public BaseWeapon()  //Use in creation of shop to find lowest priced weapon
    { 
        cost = Integer.MAX_VALUE; 
    }

    public BaseWeapon(String itemName, int cost, int minDamage, int maxDamage, String damageType, String weaponType) 
    {
        this.itemName = itemName;
        this.cost = cost;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.damageType = damageType;
        this.weaponType = weaponType;
    }

    public BaseWeapon(BaseWeapon weapon) //Copy Constructor to be able to clone
    {
        itemName = weapon.itemName;
        cost = weapon.cost;
        minDamage = weapon.minDamage;
        maxDamage = weapon.maxDamage;
        damageType = weapon.damageType;
        weaponType = weapon.weaponType; 
    }


    @Override public Item clone() //To be able to clone weapons
    {
        return new BaseWeapon(this);
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
        return rand.nextInt((maxDamage - minDamage) + 1) + minDamage; 
    }

    /* Shop toString */
    @Override public String toShopString()
    {

        String stats = itemName + ": [Buy Price: " + cost + "] [Damage: " + minDamage + " - " + maxDamage + 
        "] [Damage Type: " + damageType + "] [Weapon Type: " + weaponType + "]\n    Enchants: ";

        return stats;
    }

    /* Regular toString */
    @Override public String toString()
    {
        return itemName + ":  [Damage: " + minDamage + " - " + maxDamage + "] Enchants: ";
    } 

}