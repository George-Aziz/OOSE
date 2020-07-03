package controller;

import model.*;
import view.GeneralView;
import view.PlayerView;
import java.util.*;

public class EquipArmour implements EquipItem 
{
    public void equipItem(Player player, GeneralView genView, PlayerView playView)
    {
        String itemType = "Armour";
        Item curEquip = player.getEquppedArmour();
        List<Item> armoursList = player.getArmours();
        int exitNum = armoursList.size();
        playView.displayItems("Armour", armoursList);
        genView.toExitMsg(exitNum);
        playView.displayEquippedItem(itemType, curEquip.toString());

        String promptMsg = "\nPlease input the armour number which you would like to equip: ";
        String errorMsg = "ERROR: Please select a number between 0 and " + exitNum + "!";
        int itemNum = genView.inputInteger(promptMsg, errorMsg, 0, exitNum);
        if(itemNum == exitNum)
        {
            genView.exitMessage("Armour Equip Menu");
        }
        else
        {
            Item selectedArmour = armoursList.get(itemNum);
            if (curEquip == selectedArmour)
            {
                genView.displayErrorMessage("Armour Equip", "The selected armour has already been equipped");
            }
            else 
            { 
                player.equipArmour(selectedArmour);
            } 
        }
    }
}