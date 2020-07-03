package model;
public class DamageAddFive extends Enchants
{
    private String enchantName = "Damage +5";
    private int cost = 10;
    private int effect = 5;
    private String effectDesc = "Adds 5 to attack damage";

    public DamageAddFive() { }
    public DamageAddFive(Weapon next)
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
        return next.getEffect() + effect;
    }

    @Override public int getEnchantCost()
    {
        return this.cost;
    } 

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