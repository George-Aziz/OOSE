package view;

import model.*;
import java.util.*;

/* User Interface/ View for general usage such as user input or main menu */
/* This File has 2 methods (inputInteger & inputPlayerName) that have been based off code from my DSA Assignment */

/* Aziz, George. (2019). DSA Assignment */
public class GeneralView 
{
    Scanner sc;
    public GeneralView(Scanner sc)
    {
        this.sc = sc;
    }

    /** 
     * Display Main menu to player
     * @param player (String)
     */
    public void mainMenu(Player player)
    {
        System.out.println("\n\n========================MAIN MENU========================");
        System.out.println("[1] Start Battle");
        System.out.println("[2] Go to Shop");
        System.out.println("[3] Choose Character Name");
        System.out.println("[4] Equip Weapon");
        System.out.println("[5] Equip Armour");
        System.out.println("[6] View Inventory");
        System.out.println("[7] Exit Game");
        System.out.println("=========================================================\n");
    }
    
    /** 
     * Displays error message for when an error occurs
     * @param action (String)
     * @param cause (String)
     */
    public void displayErrorMessage(String action, String cause)
    {
        System.out.println("ERROR: " + action + " - " + cause);
    }
    
    /** 
     * Displays option number to exit when needed
     * @param exitNum (int)
     */
    public void toExitMsg(int exitNum)
    {
        System.out.println("[" + exitNum + "] To Exit\n");
    }
    
    /** 
     * Displays exit message when player decides to exit
     * @param locaiton (String)
     */
    public void exitMessage(String locaiton)
    {
        System.out.println("You have selected to exit the " + locaiton);   
    }

    /**
     * Input Validation for integers 
     * @param prompt (String)
     * @param error (String)
     * @param min (int)
     * @param max (int)
     * @return int (The selected integer by player)
     */
    public int inputInteger(String prompt, String error, int min, int max)
	{
		String outputString;
		int integerInput = min - 1; //By default the input is always invalid to ensure loop keeps looping even if nothing is inputted
		outputString = prompt; //The output is set to the prompt that is imported when method is called       
		do
		{
			try
			{
				System.out.print(outputString); //Outputs the prompt for the player
				outputString = error + "\n" + prompt; //Makes the output prompt include the error if input is invalid
				integerInput = sc.nextInt(); //User inputs an integer
			}
			catch (InputMismatchException e)
			{
				sc.nextLine(); //advances scanner to the next line
			}
		}
		while ((integerInput < min || integerInput > max)); //Validation boundaries for integers that get imported
		return integerInput;
    }

    
    /**
     * Input validation for when player decides to choose a new name 
     * @param prompt (String)
     * @param error (String)
     * @return String (Player Name)
     */
    public String inputPlayerName(String prompt, String error)
	{
		String outputString, inputPlayerName;
        outputString = prompt; //The output is set to the prompt that is imported when method is called 
		inputPlayerName = null; //By default player name is null to be invalid 
		do
		{
			System.out.println(outputString); //Outputs the prompt for the user
			outputString = error + "\n" + prompt; //Makes the output prompt include the error if input is invalid
            sc.nextLine(); //Clears Input Buffer to prevent issues
            inputPlayerName = sc.nextLine(); //User inputs a new player name
		}
		while ((inputPlayerName == null) || (inputPlayerName.isEmpty()));
		return inputPlayerName;
    }
}