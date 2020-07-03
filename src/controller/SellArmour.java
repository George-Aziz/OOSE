package controller;

import java.util.List;

import model.Item;
import model.Player;
import view.GeneralView;

/* Strategy for selling Armour */
public class SellArmour implements ShopSell 
{
    private ShopController shopController;

    public SellArmour(ShopController shopController)
    {
        this.shopController = shopController;
    }

    /**
     * Sell Armour Method
     * @param player
     * @return boolean
     */
    @Override
    public boolean sellItem(Player player, GeneralView view) 
    {
        List<Item> armours = player.getArmours(); //gets armours from player
        boolean done = false;
        int itemNum = shopController.selectSellItem("Armours", armours); //Sell Armour menu
        if(itemNum != -1)
        {
            Item selectedArmour = armours.get(itemNum);
            if(selectedArmour == player.getEquppedArmour()) //Can't sell if the armour is the same as equipped armour
            {
                view.displayErrorMessage("Selling Armour", "You currently have this armour piece equipped!\n");
            } 
            else 
            { 
               shopController.sellItem(player, selectedArmour); //Sells the item
            }
        }
        else 
        { 
            done = true; //Option from input was to exit
        }
        return done;
    }
    
}