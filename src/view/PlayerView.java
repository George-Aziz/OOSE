package view;

import model.*;
import java.util.*;

/* User Interface/View for anything related to player */
public class PlayerView 
{
    /** 
     * To Display list of specefic type of item
     * @param type (String)
     * @param items (List of Items)
     */
    public void displayItems(String type, List<Item> items)
    {
        System.out.println("\n" + type.toUpperCase() + "------------------------------------------------------------------------------\n");
        if (items.size() == 0)
        {
            System.out.println("There are no " + type + " to be shown\n");
        }
        else
        {
            int count = 0;
            for(Item curItem : items) { System.out.println("[" + count + "] " + curItem.toShopString() + "\n"); count++; }
        }
    }

    /**
     * To Display Player's Inventory 
     * @param weapons (List of Items)
     * @param armours (List of Items)
     * @param potions (List of Items)
     */
    public void displayInventory(List<Item> weapons, List<Item> armours, List<Item> potions)
    {
        System.out.println("\n=======================================INVENTORY=======================================");
        displayItems("Weapons", weapons);
        displayItems("Armours", armours);
        displayItems("Potions", potions);
        System.out.println("=======================================================================================\n");
    }

    /**
     * To Display the currently equipped Item 
     * @param itemType (String)
     * @param itemToString (String)
     */
    public void displayEquippedItem(String itemType, String itemToString)
    {
        System.out.println("Currently Equipped " + itemType + " - " + itemToString);
    }
    
    /** 
     * To Display Player's Stats
     * @param player 
     */
    public void playerStats(Player player)
    {
        System.out.println("\n+++++++++++++++++++++++++PLAYER STATS+++++++++++++++++++++++++");
        System.out.println("Name: " + player.getName());
        System.out.println("Health: " + player.getCurHealth());
        System.out.println("Gold: " + player.getGold() + "\n");
        displayEquippedItem("Weapon", player.getEquppedWeapon().toString());
        displayEquippedItem("Armour", player.getEquppedArmour().toString());
        System.out.print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }
}