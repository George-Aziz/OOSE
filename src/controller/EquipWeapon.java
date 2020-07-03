package controller;

import model.*;
import view.GeneralView;
import view.PlayerView;
import java.util.*;

public class EquipWeapon implements EquipItem 
{

    public void equipItem(Player player, GeneralView genView, PlayerView playView)
    {
        String itemType = "Weapon";
        Item curEquip = player.getEquppedWeapon();
        List<Item> weaponsList = player.getWeapons();
        int exitNum = weaponsList.size();
        playView.displayItems("Weapons", weaponsList);
        genView.toExitMsg(exitNum);
        playView.displayEquippedItem(itemType, curEquip.toString());

        String promptMsg = "\nPlease input the weapon number which you would like to equip: ";
        String errorMsg = "ERROR: Please select a number between 0 and " + exitNum + "!";
        int itemNum = genView.inputInteger(promptMsg, errorMsg, 0, exitNum);
        if(itemNum == exitNum)
        {
            genView.exitMessage("Weapon Equip Menu");
        }
        else
        {
            Item selectedWeapon = weaponsList.get(itemNum);
            if (curEquip == selectedWeapon)
            {
                genView.displayErrorMessage("Weapon Equip", "The selected weapon has already been equipped");
            }
            else 
            { 
                player.equipWeapon(selectedWeapon);
            } 
        }
    }
}