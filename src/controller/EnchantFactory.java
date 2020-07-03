package controller;

import model.*;

public class EnchantFactory 
{
    /** 
     * @param enchantName
     * @param bWeapon
     * @return Weapon (Cant be items since enchants can only wrap Weapons)
     */
    public Item enchantWeapon(String enchantName, Weapon bWeapon)
    {
        Item enchantedWeapon = null;
        if(enchantName.equals("Damage +5"))
        {
            enchantedWeapon = new DamageAddFive(bWeapon);
        }
        else if(enchantName.equals("Damage +2"))
        {
            enchantedWeapon = new DamageAddTwo(bWeapon);
        }
        else if(enchantName.equals("Power-Up"))
        {
            enchantedWeapon = new PowerUp(bWeapon);
        }
        else if(enchantName.equals("Fire Damage"))
        {
            enchantedWeapon = new FireDamage(bWeapon);
        }

        return enchantedWeapon;
    }
}