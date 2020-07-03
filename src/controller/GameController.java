package controller;

import model.*;
import view.*;
import exceptions.*;

/* A general game controller, contains methods such as main menu and settng the game up */
public class GameController 
{
    private GeneralView genView;
    private PlayerView playView;
    private ShopController shopC;
    private PlayerController playerC;
    private BattleController battleC;

    public GameController(PlayerController playerC, ShopController shopC, BattleController battleC, GeneralView genView, PlayerView playView)
    {
        this.genView = genView;
        this.playView = playView;
        this.playerC = playerC;
        this.shopC = shopC;
        this.battleC = battleC;
    }

    /**
     * Responsible for setting the game up my loading shop items and equipping lowest priced items to user  
     * @param shop
     * @param shopLoader
     * @throws InvalidShopException
     */
    public void setupGame(Shop shop,ShopLoader shopLoader) throws InvalidShopException 
    {
        try
        {
            int count = shopLoader.loadItems(shop);
            if(count == 0) //If no line has been read (file doesn't exist)
            { 
                throw new InvalidShopException("Loading Shop Error");
            } 
        }
        catch(FileErrorException e) //If the file contains errors
        { 
            throw new InvalidShopException("Invalid Shop Error",e); 
        } 

        playerC.startGameEquip(shop); //equips the player with the lowest priced weapon & armour
    }
    
    /**
     * Method that handles the main menu for the game 
     * @param player
     * @param shop
     */
    public void mainMenu(Player player,Shop shop)
    {
        boolean done = false;
        boolean gameComplete = false;
        String promptMsg = "\nPlease select an option: ";
        String errorMsg = "ERROR: Please select a number between 1 and 7!";
        do
        {
            genView.mainMenu(player);
            playView.playerStats(player);
            int option = genView.inputInteger(promptMsg, errorMsg, 1, 7);
            switch(option)
            {
                case 1: //Enter Battle
                    gameComplete = battleC.battle(player);
                    if(gameComplete == true)
                    {
                        gameOver(player, shop);
                    }
                    break;
                case 2: //Enter Shop
                    shopC.mainShopMenu(player,playerC);
                    break;
                case 3: //Assign new name for the player
                    playerC.assignPlayerName();
                    break;
                case 4: //Equip a new weapon
                    playerC.equipItem("Weapon");
                    break;
                case 5://Equip a new weapon
                    playerC.equipItem("Armour");
                    break;
                case 6: //Display Player's inventory
                    playerC.displayInventory(); 
                    break;
                case 7: //Exit the game
                    done = true;
                    break;
            }
        }while(!done);
    }

    /**
     * If player dies or kills dragon
     * @param player
     * @param shop
     */
    private void gameOver(Player player,Shop shop)
    {
        playerC.resetPlayer(shop); 
        battleC.resetEnemyProbabilities(); 
    }
}