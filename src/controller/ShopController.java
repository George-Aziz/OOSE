package controller;

import model.*;
import view.*;

import java.util.*;

public class ShopController 
{
    private GeneralView genView;
    private ShopView shopView;
    private PlayerView playView;
    private Shop shop;
    private EnchantFactory enchantFactory;
    private Map<Integer,ShopSell> sellStrategy;
    
    public ShopController(Shop shop, EnchantFactory enchantFactory, GeneralView genView, ShopView shopView, PlayerView playView)
    {
        this.genView = genView;
        this.shopView = shopView;
        this.playView = playView;
        this.shop = shop;
        this.enchantFactory = enchantFactory;
    }
    
    /** 
     * Provides the map which will be used to know how to sell items
     * @param sellStrategy (Map)
     */
    public void setSellStrategy(Map<Integer,ShopSell> sellStrategy)
    {
        this.sellStrategy = sellStrategy;
    }

    /** 
     * Method that connects all shop methods together to be able to buy/sell
     * @param player
     * @param playerController
     */
    public void mainShopMenu(Player player, PlayerController playerController)
    {
        String promptMsg = "\nPlease select an option: ";
        String errorMsg = "ERROR: Please select a number between 1 and 5!";
        boolean done = false;
    
        do
        {
            shopView.displayShopMenu();
            playView.playerStats(player);
            int option = genView.inputInteger(promptMsg, errorMsg, 1, 5);
            switch(option)
            {
                case 1: //Buy Armour/Weapon/Potion
                    buyItems(player);
                    break;
                case 2: //Buy Enchants
                    buyEnchants(player);
                    break;
                case 3: //Sell Weapon/Armour/Potion
                    shopSell(player);
                    break;
                case 4: //Display Player's Inventory
                    playerController.displayInventory(); 
                    break;
                case 5: //Exit out of shop
                    done = true;
                    break;
            }
        }while(!done);
    }

    
    /** 
     * Method responsible for buying Items such as weapons/armours/potions
     * @param player
     */
    private void buyItems(Player player) 
    {
        boolean done = false;
        do 
        {
            int itemNum = selectBuyShopItem(); //Select item to buy
            if (itemNum == -1) //If Player has selected to exit
            {
                done = true;
                genView.exitMessage("Buy Shop"); //Exit from "Buy Shop" 
            }
            else
            {
                List<Item> shopItems = shop.getShopItems();
                Item selectedItem = shopItems.get(itemNum); //Gets the item selected
                if (player.getInventorySize() < 15) //Makes sure there is enough space in inventory
                {
                    int curAmount = player.getGold(); 
                    int price = selectedItem.getCost();
                    if (curAmount - price < 0) //Makes sure player has enough gold to purchase the item
                    {
                        genView.displayErrorMessage("Buying Item", "You do not have enough gold to purchase this item!\n");
                    }
                    else
                    {
                        player.setGold(curAmount - price); //Takes the price of item away from player's gold
                        //Clones the item since can't have multiple references to same item or else will lead to issues
                        Item newItem = selectedItem.clone(); 
                        newItem.addToPlayer(player); //Adds the item to player
                        shopView.purchasedItemDisplay(newItem.getName(), price, player.getGold()); //Buying from shop feedback prompt
                    }
                }
                else
                {
                    genView.displayErrorMessage("Buying Item", "You have reached maximum inventory capacity!\n");
                }
            }
        }while(!done);
    }

    
    /** 
     * Method responsible for allowing players to be able to purchase enchants
     * @param player
     */
    private void buyEnchants(Player player)
    {
        boolean done = false;
        do
        {
            int itemNum = selectWeaponItem(player); //Weapon select
            if(itemNum == -1) //Player has decided to exit
            {
                done = true;
            }
            else
            {
                List<Item> weapons = player.getWeapons(); //Gets all player's weapons
                Item selectedWeapon = weapons.get(itemNum); //gets the selected weapon
                
                int option = selectEnchantItem(); //Enchant select
                if(option != -1)
                {
                    List<Enchants> enchantsList = shop.getEnchants();
                    Enchants selectedEnchant = enchantsList.get(option); //Gets the selected Enchant

                    int curAmount = player.getGold();
                    int price = selectedEnchant.getEnchantCost();
                    if (curAmount - price < 0) //Makes sure player has enough gold to purchase enchant
                    {
                        genView.displayErrorMessage("Buying Enchant", "You do not have enough gold to purchase this enchant!\n");
                    }
                    else
                    {
                        player.setGold(curAmount - price); 
                        String enchantName = selectedEnchant.getEnchantName();
                        //Typecast since only weapons are allowed to be decorated 
                        Item enchantedWeapon = enchantFactory.enchantWeapon(enchantName,(Weapon)selectedWeapon); 
                        player.removeWeapon(selectedWeapon); //removes unenchanted weapon from inventory
                        player.addWeapon(enchantedWeapon);
                        if (player.getEquppedWeapon() == selectedWeapon) // If the enchanted weapon is the equpped weapon, re-equip it
                        {
                            player.equipWeapon(enchantedWeapon);
                        }
                        shopView.purchasedEnchantDisplay(enchantedWeapon.getName(), enchantName ,price, player.getGold()); //Buying Enchant feedback to player
                        done = true;
                    }
                }
            }
        }while(!done);
    }
    
    /** 
     * Method responsible to select and sell items
     * @param player
     */
    private void shopSell(Player player)
    {
        boolean done = false;
        do
        {
            String promptMsg = "\nPlease select the item type of the item you would like to sell: ";
            String errorMsg = "ERROR: Please select a number between 1 and 4!";
            shopView.sellShopMenu();
            playView.playerStats(player);
            int option = genView.inputInteger(promptMsg, errorMsg, 1, 4);
            if(option == 4)
            {
                done = true;
                genView.exitMessage("Sell Shop");
            }
            else
            {
                //Depending on the option it will get strategy to sell the selected type of weapon
                ShopSell shopStrategy = sellStrategy.get(option); 
                done = shopStrategy.sellItem(player, genView); //Sells the type of item
            }
        }
        while(done == false);
    }

    
    /**
     * Method responsible to sell the item provided 
     * @param player
     * @param selectedItem
     */
    public void sellItem(Player player, Item selectedItem)
    {
        int sellPrice = selectedItem.getCost() / 2;  //Halves price of item  
        selectedItem.removeFromPlayer(player); //Removes the item
        int gold = player.getGold();
        player.setGold(gold + sellPrice); //Gives gold to player
        shopView.soldItemDisplay(selectedItem.getName(), sellPrice, player.getGold()); //Shop sell feedback
    }
    
    
    /** 
     * To allow player to select the item to sell
     * @param type
     * @param itemsList
     * @return int (item number)
     */
    public int selectSellItem(String type,List<Item> itemsList)
    {
        String promptMsg = "Select the item number of which you would like to sell: ";
        String errorMsg = "ERROR: Invalid option!";
        playView.displayItems(type, itemsList); //Displays the provided list to the player
        int exitNum = itemsList.size();
        genView.toExitMsg(exitNum);
        int itemNum = genView.inputInteger(promptMsg, errorMsg, 0, exitNum); //Player selects item
        if (itemNum == exitNum)
        {
            itemNum = -1; //-1 to resembles done
            genView.exitMessage("Sell  Shop");
        }        
        return itemNum;
    }

    
    /** 
     * To allow player to select which item to purchase
     * @return int (item number)
     */
    private int selectBuyShopItem()
    {
        List<Item> shopItems = shop.getShopItems(); //Gets shop items
        String promptMsg = "Select the item number of which you would like to purchase: ";
        String errorMsg = "ERROR: Invalid option!";
        int exitNum = shopItems.size();
        shopView.displayShopItems(shopItems, exitNum); 
        int itemNum = genView.inputInteger(promptMsg, errorMsg, 0, exitNum); //Player selects shop item to purchase
        if (itemNum == exitNum)
        {
            itemNum = -1; //-1 to resembles done
        }        
        return itemNum;
    }

    /**
     * To allow player to select which weapon to be enchanted 
     * @param player
     * @return int (item number)
     */
    private int selectWeaponItem(Player player)
    {
        List<Item> weapons = player.getWeapons();
        String promptMsg = "\nPlease input the item number of the weapon you would like to enchant: ";
        int weaponAmount = weapons.size();
        String errorMsg = "ERROR: Please select a number between 0 and " + weaponAmount + "!";
        playView.displayItems("Weapons",weapons);
        genView.toExitMsg(weaponAmount);

        int itemNum = genView.inputInteger(promptMsg, errorMsg, 0, weaponAmount); //Player selects weapon to get enchanted
        if(itemNum == weaponAmount)
        {
            itemNum = -1; //-1 resembles done
            genView.exitMessage("Weapon Selection - Enchant Shop");
        }
        return itemNum;
    }

    /** 
     * To allow player to select which enchant to purchase
     * @return int (item number)
     */
    private int selectEnchantItem()
    {
        List<Enchants> enchantsList = shop.getEnchants();
        int exitNum = enchantsList.size();
        
        shopView.displayEnchants(enchantsList, exitNum); //Display enchants to player
        String promptMsg = "\nPlease input the enchant number which you would like to apply: ";
        String errorMsg = "ERROR: Please select a number between 0 and " + exitNum + "!";
        
        int option = genView.inputInteger(promptMsg,errorMsg,0, exitNum); //Player selects enchant to purchase
        if(option == exitNum)
        {
            option = -1; //-1 to resembles done
            genView.exitMessage("Enchant Shop");
        }
        return option;
    }
}