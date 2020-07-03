package controller;

import model.*;
import view.*;

/* Strategy for how Ogres attack */
public class OgreAttack implements EnemyAttack
{
    private Enemy ogre;

    public OgreAttack(Enemy ogre)
    {
        this.ogre = ogre;
    }

    /**
     * Ogre's attack method 
     * @param battleView 
     * @return int (The damage dealt)
     */
    @Override public int attack(BattleView battleView) 
    {
        int damage = ogre.attack(); //Base Damage
        
        if(Math.random() < 0.2) //20% chance for ogre to do double attack (not double damage)
        {
            damage = damage + ogre.attack(); //Double attacks
            battleView.displaySpecialAbility(ogre.getName(), "Double Attack!");
        }

        return damage;
    }
    
}