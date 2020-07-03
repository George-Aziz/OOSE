package model;
public class DamageAddTwo extends Enchants
{
    private String enchantName = "Damage +2";
    private int cost = 5;
    private int effect = 2;
    private String effectDesc = "Adds 2 to attack damage"; 

    public DamageAddTwo() { }
    public DamageAddTwo(Weapon next)
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