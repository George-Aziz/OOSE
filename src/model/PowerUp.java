package model;
public class PowerUp extends Enchants
{
    private String enchantName = "Power-Up";
    private int cost = 10;
    private double effect = 1.1;
    private String effectDesc = "Multiplies attack damage by 1.1"; 

    public PowerUp() { }
    public PowerUp(Weapon next)
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
        return (int)(next.getEffect() * effect);
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