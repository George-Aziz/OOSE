package controller;

import model.Enemy;
import view.BattleView;

/* Strategy for how Goblins attack */
public class GoblinAttack implements EnemyAttack
{
    private Enemy goblin;

    public GoblinAttack(Enemy goblin)
    {
        this.goblin = goblin;
    }
    
    /**
     * Goblin's attack method 
     * @param battleView 
     * @return int (The damage dealt)
     */
    @Override public int attack(BattleView battleView) 
    {
        int damage = goblin.attack(); //Base Damage
        
        if(Math.random() < 0.5) //50% chance for special ability to be triggered
        {
            damage = damage + 3; //Adds 3 damage to the base damage amount
            battleView.displaySpecialAbility(goblin.getName(), "+3 Attack Damage!");
        }
        return damage;
    }    
}