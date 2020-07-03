package model;
import java.util.*;

public class Shop 
{
    private List<Item> shopItems;
    private List<Enchants> enchantsList;
    private Item lowestPricedArmour;
    private Item lowestPricedWeapon;

    public Shop()
    {
        shopItems = new LinkedList<Item>();
        enchantsList = new LinkedList<Enchants>();
        lowestPricedArmour = new Armour(); //Creates an armour with max value
        lowestPricedWeapon = new BaseWeapon(); //Creates a weapon with max value
    }
        
    /* Adds item to shop */
    public void addItem(Item item)
    {
        shopItems.add(item);
    }

    /* Adds enchant to enchantList of shop */
    public void addEnchant(Enchants enchant)
    {
        enchantsList.add(enchant);
    }

    /* Retrieves the shop items */
    public List<Item> getShopItems()
    {
        return this.shopItems;
    }

    /* Retrieves the enchants  */
    public List<Enchants> getEnchants()
    {
        return this.enchantsList;
    }

    /* Set lowest priced armour in shop */
    public void setLowestPricedArmour(Item lowestPricedArmour) 
    {
        this.lowestPricedArmour = lowestPricedArmour;
    }

    /* Set lowest priced weapon in shop */
    public void setLowestPricedWeapon(Item lowestPricedWeapon) 
    {
        this.lowestPricedWeapon = lowestPricedWeapon;
    }
    
    /* Gets the lowest priced armour */
    public Item getLowestPricedArmour() 
    {
        return lowestPricedArmour;
    }

    /* Gets the lowest priced weapon */
    public Item getLowestPricedWeapon() 
    {
        return lowestPricedWeapon;
    }

    /* Clear shop for if shop loading has error */
    public void clearShop()
    {
        shopItems = new LinkedList<Item>();
    }

}