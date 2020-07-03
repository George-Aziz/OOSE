package controller;

import model.*;
import exceptions.InvalidItemException;

public class ItemFactory 
{
    /**
     * Factory Method that creates new items to be loaded into shop 
     * @param lineArray[]
     * @return Item 
     */
    public Item makeItem(String lineArray[]) throws InvalidItemException 
    {
        Item newItem = null;
        try
        {
            String itemName = lineArray[1].trim();
            int minEffect = Integer.parseInt(lineArray[2].trim());
            int maxEffect = Integer.parseInt(lineArray[3].trim());
            int cost = Integer.parseInt(lineArray[4].trim());

            if(itemName == null || itemName.isEmpty() || minEffect == 0 || maxEffect == 0 || cost == 0)//Any invalid array element
            {
                throw new InvalidItemException("One or more line elements are null/empty!"); 
            }
            else 
            {
                if(lineArray[0].equals("W")) //If Item is of Weapon Type
                {
                    if(lineArray.length != 7) //Item has 7 line elements
                    {
                        throw new InvalidItemException("Weapon must have 7 elements");
                    }
                    else
                    {
                        String damageType, weaponType;
                        damageType = lineArray[5].trim();
                        weaponType = lineArray[6].trim();
                        if(weaponType == null || damageType == null || weaponType.isEmpty() || damageType.isEmpty())
                        {
                            throw new InvalidItemException("Weapon/Damage type cannot be null/empty!"); 
                        }
                        else
                        {
                            newItem = new BaseWeapon(itemName, cost, minEffect, maxEffect, damageType, weaponType);
                        }
                    }
                }
                else if (lineArray[0].equals("A")) //If Item is of Armour Type
                {
                    String material;
                    material = lineArray[5].trim();
                    if(material == null || material.isEmpty())
                    {
                        throw new InvalidItemException("Material of armour cannot be null/empty!"); 
                    }
                    else 
                    {
                        newItem = new Armour(itemName, cost, minEffect, maxEffect, material);
                    }
                }
                else if (lineArray[0].equals("P")) //If Item is of Potion type
                {
                    char effectType; //Default to damage type
                    if ((lineArray[5].trim()).equals("H")) 
                    { 
                        effectType = 'H'; 
                    }
                    else if ((lineArray[5].trim()).equals("D")) 
                    { 
                        effectType = 'D'; 
                    }
                    else //If the effect type is not D or H then it is Invalid 
                    {
                        throw new InvalidItemException("Invalid Potion type");
                    }

                    newItem = new Potion(itemName, cost, minEffect, maxEffect, effectType);
                }
            }
        }
        catch(NumberFormatException e)
        {
            throw new InvalidItemException("Min/Max Effect & costs must be integers",e);
        }

        return newItem;
    }    
}