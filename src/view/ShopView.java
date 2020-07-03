package view;

import model.*;
import java.util.*;

/* User Interface/View for anything shop related */
public class ShopView 
{
    /**
     * To display Shop Items 
     * @param items (List of Items)
     * @param exitNum (int)
     */
    public void displayShopItems(List<Item> items, int exitNum)
    {
        System.out.println("\n==========================================================Items Shop==========================================================\n");
        int count = 0;
        for(Item curItem : items) { System.out.println("[" + count + "] " + curItem.toShopString() + "\n"); count++; }
        System.out.println("["+ exitNum +"] To Exit\n");
        System.out.println("==============================================================================================================================\n");
    }
    
    /** 
     * To display enchants from shop
     * @param enchants (List of Enchants)
     * @param exitNum (int)
     */
    public void displayEnchants(List<Enchants> enchants, int exitNum)
    {
        System.out.println("\n==========================================================Enchants Shop==========================================================\n");
        int count = 0;
        for(Enchants curEnchant : enchants) { System.out.println("[" + count + "] " + curEnchant.toEShopString() + "\n"); count++; }
        System.out.println("["+ exitNum +"] To Exit\n");
        System.out.println("=================================================================================================================================\n");
    }

    /* To display main shop menu */
    public void displayShopMenu()
    {
        System.out.println("\n\n=========================SHOP========================");
        System.out.println("[1] Purchase Items [Weapon/Armour/Potion]");
        System.out.println("[2] Purchase & Apply Enchants");
        System.out.println("[3] Sell Items");
        System.out.println("[4] Display Inventory");
        System.out.println("[5] To Exit");
        System.out.println("=====================================================\n");
    }

    /* To display selling shop menu */ 
    public void sellShopMenu()
    {
        System.out.println("\n\n===================SELL SHOP==================");
        System.out.println("NOTE: Items get sold for 50% off price ");
        System.out.println("For Weapons, 50% off price + any cost of enchants");
        System.out.println("[1] Weapons ");
        System.out.println("[2] Armours ");
        System.out.println("[3] Potion");
        System.out.println("[4] To Exit");
        System.out.println("===============================================\n");
    }

    /**
     * To display to Player what item they have purchased and new balance 
     * @param itemName (String)
     * @param price (int)
     * @param newBal (int)
     */
    public void purchasedItemDisplay(String itemName, int price, int newBal)
    {
        System.out.println("You have purchased " + itemName + " for " + price + " gold");
        System.out.println("Your new balance is " + newBal + " gold" );
    }

    /**
     * To display to Player what enchant they have purchased
     * @param itemName (String)
     * @param enchantName (String)
     * @param price (int)
     * @param newBal (int)
     */ 
    public void purchasedEnchantDisplay(String itemName, String enchantName, int price, int newBal)
    {
        System.out.println("You have applied the " + enchantName + " enchant onto " + itemName + " for " + price + " gold");
        System.out.println("Your new balance is " + newBal + " gold" ); 
    }

    
    /**
     * To display to Player how much they have received + their new balance
     * @param itemName (String)
     * @param price (int)
     * @param newBal (int)
     */
    public void soldItemDisplay(String itemName,int price, int newBal)
    {
        System.out.println("You have sold " + itemName + " for " + price + " gold");
        System.out.println("Your new balance is " + newBal + " gold");
    }

}