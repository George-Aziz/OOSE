package model;
import java.util.*;

/* Player class to hold all info related to player */
public class Player 
{
    private String name;
    private int maxHealth;
    private int curHealth;
    private Item equippedWeapon;
    private Item equippedArmour;
    private List<Item> armours;
    private List<Item> weapons;
    private List<Item> potions;
    private int gold;

    public Player()
    {
        name = "Default";
        maxHealth = 30;
        curHealth = maxHealth;
        gold = 100;
        weapons = new LinkedList<Item>();
        armours = new LinkedList<Item>();
        potions = new LinkedList<Item>();
    }

    /** 
     * Player attack with current equipped weapon
     * @return int (damage)
     */
    public int attack()
    {
        return equippedWeapon.getEffect();
    }

    /** 
     * Player defence with current equipped armour
     * @param damage (Damage by Enemy)
     * @return int (amount of defence)
     */
    public int defend(int damage)
    {
        int defence = equippedArmour.getEffect();
        this.curHealth = curHealth - Math.max(0, (damage - defence));
        if(curHealth < 0)
        {
            this.curHealth = 0;
        }
        return defence;
    }

    public void equipArmour(Item armour)
    {
        equippedArmour = armour;
    }

    public void equipWeapon(Item weapon)
    {
        equippedWeapon = weapon;
    }

    public void addArmour(Item armour)
    {
        this.armours.add(armour);
    }

    public void addWeapon(Item weapon)
    {
        this.weapons.add(weapon);
    }

    public void addPotion(Item potion)
    {
        this.potions.add(potion);
    }

    public void removeWeapon(Item weapon)
    {
        this.weapons.remove(weapon);
    }

    public void removeArmour(Item armour)
    {
        this.armours.remove(armour);
    }

    public void removePotion(Item potion)
    {
        this.potions.remove(potion);
    }

    public String getName() 
    { 
        return this.name; 
    }

    public int getGold() 
    { 
        return this.gold; 
    }

    public int getMaxHealth() 
    { 
        return this.maxHealth; 
    }

    public int getCurHealth() 
    { 
        return this.curHealth; 
    }

    public List<Item> getArmours() 
    { 
        return this.armours; 
    }

    public List<Item> getWeapons() 
    { 
        return this.weapons; 
    }

    public List<Item> getPotions() 
    { 
        return this.potions; 
    }

    public Item getEquppedWeapon() 
    { 
        return this.equippedWeapon; 
    } 

    public Item getEquppedArmour() 
    { 
        return this.equippedArmour; 
    } 


    public int getInventorySize() 
    {
        int armoursSize = armours.size();
        int weaponSize = weapons.size();
        int potionsSize = potions.size();

        return armoursSize + weaponSize + potionsSize;
    }

    public void setGold(int gold) 
    { 
        this.gold = gold; 
    }

    public void setName(String name) 
    { 
        this.name = name; 
    }

    public void setCurHealth(int health) 
    { 
        this.curHealth = health; 
    }

    /* Reset inventory if game is over (Lost to enemy or slain dragon) */
    public void resetInventory()
    {
        weapons = new LinkedList<Item>();
        armours = new LinkedList<Item>();
        potions = new LinkedList<Item>();
    }
        
}