package model;
import java.util.*;

/* One class for all enemy types as they all have same methods */
public class Enemy
{
    private String name;
    private int maxHealth;
    private int curHealth;
    private int minDamage;
    private int maxDamage;
    private int minDefence;
    private int maxDefence;
    private int coinReward;

    public Enemy(String name, int maxHealth, int curHealth, int minDamage, int maxDamage,
                   int minDefence, int maxDefence, int coinReward) 
    {
        this.name = name;
        this.maxHealth = maxHealth;
        this.curHealth = curHealth;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
        this.coinReward = coinReward;
    }

    /** 
     * Enemy normal attack
     * @return int (damage)
     */
    public int attack() 
    {
        Random rand = new Random();
        return rand.nextInt((maxDamage - minDamage) + 1) + minDamage;
    }
    
    /** 
     * Enemy normal defence
     * @param damage (Damage by player)
     * @return int (amount of defence)
     */
    public int defend(int damage)
    {
        Random rand = new Random();
        int defence = rand.nextInt((maxDefence - minDefence) + 1) + minDefence;
        this.curHealth = curHealth - Math.max(0, (damage - defence));
        if(curHealth < 0)
        {
            this.curHealth = 0;
        }

        return defence;
    }

    public void setHealth(int health)
    {
        this.curHealth = health;
    }

    public int getHealth()
    {
        return this.curHealth;
    }

    public int getMaxHealth()
    {
        return this.maxHealth;
    }

    public String getName()
    {
        return this.name;
    }

    public int getReward()
    {
        return this.coinReward;
    }

}