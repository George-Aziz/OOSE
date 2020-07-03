package controller;

import model.*;
import view.*;

import java.util.*;

/* A controller for the battle feature in the game */
public class BattleController 
{
    private GeneralView genView;
    private PlayerView playView;
    private BattleView battleView;
    private EnemyFactory factory;
    private Map<String,EnemyAttack> attackStrategy;
    private PlayerController playerC;
    
    public BattleController(EnemyFactory factory, PlayerController playerC, GeneralView genView, PlayerView playView, 
                            BattleView battleView, Map<String,EnemyAttack> attackStrat)
    {
        this.genView = genView;
        this.playView = playView;
        this.battleView = battleView;
        this.factory = factory;
        this.attackStrategy = attackStrat;
        this.playerC = playerC;
    }

    
    /**
     * The main battle method that runs a battle and calls any other needed methods
     * @param player 
     * @return boolean (A boolean to represent if the game is over and needs a reset)
     */
    public boolean battle(Player player)
    {
        double random = Math.random(); 
        Enemy curEnemy = factory.createEnemy(random); //Creates enemies based of the random probability
        battleView.incomingEnemy(curEnemy.getName()); //Displays to the user what enemy will be fought

        int turnCounter = 0; //Player will always begin the battle
        boolean playerIsWinner = false;
        boolean enemyIsWinner = false;
        boolean gameOver = false;
        while(playerIsWinner == false && enemyIsWinner == false)
        {
            if(turnCounter % 2 == 0) //Player is going to be even
            {
                playerIsWinner = playerTurn(player, curEnemy);
            }
            else //Enemy attacks
            {
                enemyIsWinner = enemyTurn(player, curEnemy);
            }
            turnCounter++;
        } 
        gameOver = battleCompleteCheck(player, curEnemy, playerIsWinner, enemyIsWinner);
        return gameOver;
    }

    
    /** 
     * The player's turn is handled in this method
     * @param player
     * @param curEnemy
     * @return boolean (A boolean that represents if the player has won)
     */
    private boolean playerTurn(Player player, Enemy curEnemy)
    {
        boolean winner = false;
        boolean attacked = false; //boolean to know if player has completed the turn
        String promptMsg = "\nPlease select an option: ";
        String errorMsg = "ERROR: Please select a number between 1 - 3!";
        do
        {
            battleView.displayBattleMenu(); 
            int option = genView.inputInteger(promptMsg, errorMsg, 1, 3);
            if (option == 1) //If the user has selected to do a normal weapon attack
            {
                battleView.displayTurnHeader(player.getName());
                int damage = player.attack(); 
                int defence = curEnemy.defend(damage); 
                battleView.displayTurn(curEnemy.getName(),player.getName(), defence, damage, curEnemy.getHealth()); //Displays player's turn
                if(curEnemy.getHealth() == 0)
                {
                    winner = true; //if the enemy after the attack has 0 health, player is winner
                }
                attacked = true;
            }
            else if(option == 2) //If the user has selected to use a potion
            { 
                attacked = selectPotion(player, curEnemy); 
                if(curEnemy.getHealth() == 0)
                {
                    winner = true; //if the enemy after the potion usage has 0 health, player has won
                } 
            }
            else if(option == 3) 
            { 
                playView.playerStats(player); //Displays currently equipped items as well as health,name and gold
            }
        }
        while(attacked == false);

        return winner;
    }

    
    /**
     * Method that allows player to use a potion 
     * @param player
     * @param enemy
     * @return boolean (A boolean that indicates if the player has used the turn or not)
     */
    private boolean selectPotion(Player player, Enemy enemy)
    {
        boolean attacked = false;
        List<Item> potionsList = player.getPotions();
        int potionsAmount = potionsList.size();
        if (potionsAmount > 0) //Ensures that the player has at least 1 potion
        {
            String promptMsg = "\nPlease select the potion you would like to use: ";
            String errorMsg = "ERROR: ERROR: Please select a number between 0 and " + potionsAmount + "!";
            playView.displayItems("Potions", player.getPotions()); 
            genView.toExitMsg(potionsAmount);
            int selectPot = genView.inputInteger(promptMsg, errorMsg, 0, potionsAmount); //Player selects the potion
            if(selectPot == potionsAmount)
            {
                genView.exitMessage("Potions Menu");
            }
            else
            {
                battleView.displayTurnHeader(player.getName());
                Item potion = potionsList.get(selectPot);
                attacked = playerC.usePotion((Potion)potion, enemy, battleView);//Typecast needed since Potion only class with getEffectType method
            }
        }
        else //If not potion then an error message will appear notifying the player that there is no potion in inventory
        {
            genView.displayErrorMessage("Trying to use potions", "No potions available for use");
        }
        return attacked;
    }

    
    /**
     * The enemy's turn is handled in this method 
     * @param player
     * @param curEnemy
     * @return boolean (A boolean that represents if the enemy has won)
     */
    private boolean enemyTurn(Player player, Enemy curEnemy)
    {
        //Gets the attack strategy of enemy depending on enemy type
        EnemyAttack enemyAttack = attackStrategy.get(curEnemy.getName()); 
        boolean winner = false;
        battleView.displayTurnHeader(curEnemy.getName());
        int damage = enemyAttack.attack(battleView);
        int defence =  player.defend(damage);
        battleView.displayTurn(player.getName(), curEnemy.getName(), defence, damage, player.getCurHealth()); //Displays the turn of enemy
        if (player.getCurHealth() == 0) //If the player's health after attack is 0, enemy is winner
        {
            winner = true;
        }

        return winner;
    }

    
    /** 
     * A method that will check who is winner and also decide if game is over or not
     * @param player
     * @param curEnemy
     * @param playerIsWinner (boolean)
     * @param enemyIsWinner (boolean)
     * @return boolean (A boolean representing whether the game is over and game needs to be reset)
     */
    private boolean battleCompleteCheck(Player player, Enemy curEnemy, boolean playerIsWinner, boolean enemyIsWinner)
    {
        boolean gameOver = false;
        if (playerIsWinner == true)
        {
            if ((curEnemy.getName()).equals("Dragon")) //If the player has slain the dragon then the game ends
            {
                gameOver = true;
                battleView.wonGame(player.getName());
            }
            else //If the player has simply won then change enemies probabilities of enemies and reward player
            {
                //makes sure only can go down to 5% for Slime, Goblin & Ogre
                factory.setSlimeProb(Math.max(0.05,factory.getSlimeProb() - 0.05)); 
                factory.setGoblinProb(Math.max(0.05,factory.getGoblinProb() - 0.05));
                factory.setOgreProb(Math.max(0.05,factory.getOgreProb() - 0.05));
                //min since dragon increases and max 85% because other 3 enemies take total of 15%
                factory.setDragonProb(Math.min(0.85,factory.getDragonProb() + 0.15)); 
                //Rewards for player
                int maxHealth = player.getMaxHealth();
                int beforeHealth = player.getCurHealth();
                player.setCurHealth(Math.min(maxHealth, (int)(player.getCurHealth() * 1.5)));
                int newHealth = player.getCurHealth();
                player.setGold(player.getGold() + curEnemy.getReward());
                battleView.displayReward(player.getName(),curEnemy.getName() ,curEnemy.getReward(), newHealth, (newHealth - beforeHealth));
            }
        }
        else if(enemyIsWinner == true) //If the enemy has won, the game will be over and reset
        {
            gameOver = true;
            battleView.enemyWinner(curEnemy.getName(), player.getName());
        }
        return gameOver;
    }

    /* If game is over the enemies probabilities must be reset to default values */
    public void resetEnemyProbabilities()
    {
        factory.setSlimeProb(0.5);
        factory.setGoblinProb(0.3);
        factory.setOgreProb(0.2);
        factory.setDragonProb(0.0);
    }
}