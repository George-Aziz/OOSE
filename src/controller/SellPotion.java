package controller;

import java.util.List;

import model.Item;
import model.Player;
import view.GeneralView;

/* Strategy for selling Potions */
public class SellPotion implements ShopSell 
{

    private ShopController shopController;

    public SellPotion(ShopController shopController)
    {
        this.shopController = shopController;
    }

    
    /**
     * Sell Potion method 
     * @param player
     * @return boolean
     */
    @Override
    public boolean sellItem(Player player, GeneralView view) 
    {
        boolean done = false;
        List<Item> potions = player.getPotions(); //Get Player's potions
        if (potions.size() == 0) //If player has no potions then can't sell
        {
            view.displayErrorMessage("Trying to sell potions", "No potions available to be sold!");
        }
        else
        { 
            int itemNum = shopController.selectSellItem("Potions",potions); //Player selects the potion they would like to sell
            if(itemNum != -1) 
            {
                Item selectedPotion = potions.get(itemNum); //Gets the selected potion
                shopController.sellItem(player, selectedPotion); //Sells the potion
            }
            else 
            { 
                done = true; //If the player has selected to exit
            }
        }
        return done;
    }


}