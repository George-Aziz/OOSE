package controller;

import model.*;
import java.io.*;
import exceptions.*;

/* A stategy class to load items to be into the game shop through files */
public class FileManager implements ShopLoader
{
	private ItemFactory itemFactory; //Item factory that will be used to create Items per line in file
	public FileManager(ItemFactory itemFactory)
	{
		this.itemFactory = itemFactory;
	}
		
	/** 
	 * To be able to read the file loaded for shop
	 * @param shop
	 * @return int (count)
	 * @throws FileErrorException
	 */
	public int loadItems(Shop shop) throws FileErrorException
	{
		String line1;
		int count = 0;
		//Opens the shop file that is in resources directory with name of ShopFile.txt
		try(BufferedReader br1 = new BufferedReader(new FileReader("../resources/ShopFile.txt"))) 
		{
			while ((line1 = br1.readLine()) != null)
			{
				try 
				{
					processLine(line1, shop);
					count++;
				} 
				catch (InvalidItemException e) //If one of the lines are invalid
				{ 
					throw new FileErrorException("Invalid Shop Data", e); 
				}
			}
		} 
		catch (IOException e) //Once it catched IOException, it will retry to read the file again
		{
			String line2;
			shop.clearShop();
			count = 0;
			try(BufferedReader br2 = new BufferedReader(new FileReader("../resources/ShopFile.txt")))
			{
				while ((line2 = br2.readLine()) != null) 
				{
					try
					{
						processLine(line2, shop);
						count++;
					} 
					catch (InvalidItemException ex) //If one of the lines are valid
					{ 
						throw new FileErrorException("Invalid Shop Data", ex); 
					}
				}
			}
			catch(IOException ex) //If IOException caught for a second time, throw exception to notify error
			{
				throw new FileErrorException("IO Error", ex);
			}	
		}	
		
		return count;
	}
			
	/**
	 * To be able to process a line and create an item for the game's shop 
	 * @param line
	 * @param shop
     * @throws InvalidItemException
	 */
	private void processLine(String line, Shop shop) throws InvalidItemException
	{
		try
		{
			String [] lineArray = line.split(","); //After every section a ',' will be used to seperate them
			if (lineArray.length < 6) //Minimum length for araay is if it is either Armour or Potion (6)
			{
				throw new InvalidItemException("Invalid Item - Must have at least 6 values");
			}
			else
			{
				Item newItem = itemFactory.makeItem(lineArray); //Creates the item
				
				if(newItem instanceof Weapon) //If the item is a Weapon
				{
					//Gets gets the lowest priced weapon, so after shop is loaded, the weapon would be the equipped weapon for Player
					//Initially lowestPricedWeapon is Integer.MAX_VALUE
					if (newItem.getCost() < shop.getLowestPricedWeapon().getCost())
					{
						shop.setLowestPricedWeapon(newItem);
					}
				}
				else if(newItem instanceof Armour)
				{
					//Gets gets the lowest priced armour, so after shop is loaded, the armour would be the equipped armour for Player
					//Initially lowestPricedArmour is Integer.MAX_VALUE
					if (newItem.getCost() < shop.getLowestPricedArmour().getCost())
					{
						shop.setLowestPricedArmour(newItem);
					}
				}
				shop.addItem(newItem); //Adds newly created item into shop
			}
		}
		catch (InvalidItemException e) //Item Factory exceptions caught here
		{
			throw new InvalidItemException("Invaid Item!",e);//Throws the exception to above layer
		}
    }
}

