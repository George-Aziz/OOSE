package controller;

import java.util.List;
import model.Item;
import model.Player;
import view.GeneralView;

/* Strategy for selling weapons */
public class SellWeapon implements ShopSell 
{
    private ShopController shopController;

    public SellWeapon(ShopController shopController)
    {
        this.shopController = shopController;
    }

    
    /** 
     * Sell Weapon method
     * @param player
     * @return boolean
     */
    @Override public boolean sellItem(Player player, GeneralView view) 
    {
        List<Item> weapons = player.getWeapons(); //Gets the player's weapons
        boolean done = false;
        int itemNum = shopController.selectSellItem("Weapons", weapons); //Player selects which weapon to sell
        if(itemNum != -1)
        {
            Item selectedWeapon = weapons.get(itemNum); //Gets the selected weapon
            if(selectedWeapon == player.getEquppedWeapon()) //If selected weapon is the equipped weapon then cant sell
            {
                view.displayErrorMessage("Selling Weapon", "You currently have this weapon equipped!\n");
            } 
            else 
            { 
               shopController.sellItem(player, selectedWeapon); //sells the weapon
            }
        }
        else
        { 
            done = true; //Player has selected to exit 
        }
        return done;
    }
    
}