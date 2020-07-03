package controller;

import model.*;
import view.*;

/* Strategy for how slimes attacks */
public class SlimeAttack implements EnemyAttack
{
    private Enemy slime;

    public SlimeAttack(Enemy slime)
    {
        this.slime = slime;
    }

    /**
     * Slime's attack method 
     * @param battleView 
     * @return int (The damage dealt)
     */
    @Override public int attack(BattleView battleView) 
    {
        int damage = slime.attack(); //Base Damage
        
        if(Math.random() < 0.2) //20% chance for enemy special ability to happen
        {
            damage = 0; //Deals no damage
            battleView.displaySpecialAbility(slime.getName(), "No Damage Attack!");
        }
        return damage;
    }
    
}