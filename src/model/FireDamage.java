package model;
import java.util.Random;

public class FireDamage extends Enchants
{
    private String enchantName = "Fire Damage";
    private int cost = 20;
    private int minEffect = 5;
    private int maxEffect = 10;
    private String effectDesc = "Adds between 5–10 (randomly) to attack damage"; 

    public FireDamage() { }
    public FireDamage(Weapon next)
    {
        super(next);
    }

    @Override public String getEnchantName()
    {
        return enchantName;
    }

    @Override public int getCost()
    {
        return next.getCost() + cost;
    }

    @Override public int getEffect()
    {
        Random rand = new Random();
        return next.getEffect() + rand.nextInt((maxEffect - minEffect) + 1) + minEffect;
    }

    @Override public int getEnchantCost()
    {
        return this.cost;
    }

    /* Regular toString */
    @Override public String toString()
    {
        return next.toString() + "[" + enchantName + "] ";
    }

    /* Shop toString */
    @Override public String toShopString()
    {
        return next.toShopString() +  "[" + enchantName + "] ";
    }

    /* Enchants Shop toString */
    public String toEShopString()
    {
        return enchantName + " - [Price: " + cost + "] [" +  effectDesc + "]\n";
    }
    
    @Override public Item clone()
    {
        return new DamageAddFive(this);
    }
}