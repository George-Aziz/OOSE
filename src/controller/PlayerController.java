package controller;

import model.*;
import view.*;
import java.util.*;

public class PlayerController 
{
    private Player player;
    private GeneralView genView;
    private PlayerView playView;
    private Map<String,EquipItem> equipItemStrategy;

    public PlayerController(Player player, GeneralView genView, PlayerView playView, Map<String,EquipItem> equipItemStrategy)
    {
        this.player = player;
        this.genView = genView;
        this.playView = playView;
        this.equipItemStrategy = equipItemStrategy;
    }
    
    /**
     * Responsible for equipped the player with the cheapest priced armour & weapon 
     * @param shop
     */
    public void startGameEquip(Shop shop)
    {
        Item armour = shop.getLowestPricedArmour();
        Item weapon = shop.getLowestPricedWeapon();
        player.addArmour(armour);
        player.equipArmour(armour);
        player.addWeapon(weapon);
        player.equipWeapon(weapon);
    }

    /* Method responsible for changing player's name */
    public void assignPlayerName()
    {
        String promptMsg = "\nPlease input a new name for your player: ";
        String errorMsg = "ERROR: Cannot have empty name";
        String newName = genView.inputPlayerName(promptMsg, errorMsg);
        player.setName(newName);
    }

    /**
     * Responsible for equipped Item (Armour or Weapon only) to player
     * @param itemType (String that will determine which strategy to use)
     */
    public void equipItem(String itemType)
    {
        EquipItem equipStrategy = equipItemStrategy.get(itemType);
        equipStrategy.equipItem(player, genView, playView);
    }
   
    public void displayInventory()
    {
        playView.displayInventory(player.getWeapons(),player.getArmours(),player.getPotions());
    }
    
    /**
     * Responsible for resetting a player (Used when the game is over) 
     * @param shop
     */
    public void resetPlayer(Shop shop)
    {
        //PlayerName and maxhealth do not change as there is no need to
        player.setGold(100); //Default value
        player.setCurHealth(player.getMaxHealth()); //MaxHealth never changes during game, so new health is maxHealth
        player.resetInventory();//Reset inventory
        startGameEquip(shop); //Since inventory emptty, re-equips lowest priced items from shop
    }

    /** 
     * @param potion Must be potion type or else getEffectType cannot be called
     * @param enemy
     * @param battleView
     * @return boolean (To notify the method calling in battleController if the player has used a potion(did their turn))
     */ 
    public boolean usePotion(Potion potion, Enemy enemy, BattleView battleView)
    {
        boolean usedPotion = false;
        char potionType = potion.getEffectType(); //To differentiate between damage/healing potions
        if(potionType == 'H')
        {
            int curHealth = player.getCurHealth();
            player.setCurHealth(Math.min(player.getMaxHealth(), (curHealth + potion.getEffect())));
            //Display the player has used a healing potion
            battleView.displayHealPotionTurn(player.getName(), potion.getEffect(), player.getCurHealth());
            usedPotion = true;
            player.removePotion(potion);
        }
        else if (potionType == 'D')
        {
            int damage = potion.getEffect();
            int defence = enemy.defend(damage);
            //Display the player has used a damage potion
            battleView.displayTurn(enemy.getName(),player.getName(), defence, damage, enemy.getHealth());
            usedPotion = true;
            player.removePotion(potion);
        }
        return usedPotion;
    }
}