package controller;

import model.Enemy;
import view.BattleView;

/* Strategy for how dragon attacks */
public class DragonAttack implements EnemyAttack
{
    private Enemy dragon;

    public DragonAttack(Enemy dragon)
    {
        this.dragon = dragon;
    }

    /**
     * Dragon's attack method 
     * @param battleView 
     * @return int (The damage dealt)
     */
    @Override public int attack(BattleView battleView) 
    {
        int damage = dragon.attack(); //Base Damage
        double random = Math.random();
        // 35% chance total for special ability to be triggered
        if(random < 0.25) // 25% chance for damage to be doubled
        {
            damage = damage * 2;
            battleView.displaySpecialAbility(dragon.getName(), "Double Damage!");
        }
        else if (random > 0.25 && random < 0.35) // 10% chance for dragon to heal by 10HP
        {
            damage = 0;
            dragon.setHealth(Math.min(dragon.getMaxHealth(), (dragon.getHealth() + 10) ));
            battleView.displaySpecialAbility(dragon.getName(), "+10 Heal!");
        }
        return damage;
    }
    
}