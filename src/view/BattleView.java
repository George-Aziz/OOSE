package view;
/* User Interface/ View for Battles */
public class BattleView 
{
    
    /** 
     * Display to notify Player which enemy they will be fighting
     * @param enemyName (String)
     */
    public void incomingEnemy(String enemyName)
    {
        System.out.println("\n" + enemyName + " is coming to you!");
    }

    /** 
     * Display Turn Header for either Player or Enemy
     * @param name (String)
     */
    public void displayTurnHeader(String name)
    {
        System.out.println("\n================" + name.toUpperCase() + "'S TURN=================");
    }

    /** 
     * Display Turn for either Player or Enemy during battle rounds
     * @param defender (String)
     * @param attacker (String)
     * @param defence (int)
     * @param damage (int)
     * @param health (int)
     */
    public void displayTurn(String defender, String attacker, int defence, int damage, int health)
    {
        System.out.println(attacker + " has dealt " + damage + " to " + defender + "!");
        System.out.println(defender + "'s Defence: " + defence);
        System.out.println(defender + "'s Health: " + health);
        System.out.println("=============================================\n");
    }

    /**
     * Display for when player uses healing potion during battle 
     * @param name (String)
     * @param effectAmount (int)
     * @param health (int)
     */
    public void displayHealPotionTurn(String name, int effectAmount, int health)
    {
        System.out.println(name + " has decided to use a healing potion");
        System.out.println("The healing potion provided " + effectAmount + "HP");
        System.out.println(name + "'s New Health: " + health);
        System.out.println("=============================================\n");
    }
    
    /** 
     * Display for when a special ability has been triggered by enemy
     * @param enemyName (String)
     * @param specialAbility (String)
     */
    public void displaySpecialAbility(String enemyName, String specialAbility)
    {
        System.out.println(enemyName + " engaged special ability: " + specialAbility);
    }   

    /** 
     * Display for when player has won the game (slain the dragon)
     * @param playerName (String)
     */
    public void wonGame(String playerName)
    {
        System.out.println("\n\nCongratualtions " + playerName + ", you have slain the dragon and won the game!");
        System.out.println("New Game will now start. Returning to main menu...");
    }
    
    /** 
     * Displays for when enemy has won the battle
     * @param enemyName (String)
     * @param playerName (String)
     */
    public void enemyWinner(String enemyName, String playerName)
    {
        System.out.println("================ENEMY HAS WON================");
        System.out.println(enemyName + " has defeated " + playerName + "!");
        System.out.println("New Game will now start. Returning to main menu...");
        System.out.println("=============================================");
    }

    /** 
     * Displays Reward for player after battle
     * @param playerName (String)
     * @param enemyName (String)
     * @param gold (int)
     * @param newHealth (int)
     * @param diffHealth (int)
     */
    public void displayReward(String playerName, String enemyName, int gold, int newHealth, int diffHealth)
    {
        System.out.println("================PLAYER HAS WON================");
        System.out.println(playerName + " has defeated " + enemyName + "!");
        System.out.println("Received " + gold + " gold");
        System.out.println(playerName + "'s Health: " + newHealth + " [Increased by: " + diffHealth + "]");
        System.out.println("Returning to main menu...");
        System.out.println("==============================================");
    }

    /* Displays the main battle menu to allow player to decide what to do */
    public void displayBattleMenu()
    {
        System.out.println("\n[1] Attack");
        System.out.println("[2] Use Potion");
        System.out.println("[3] Display Current Stats\n");
    }
}