package model;

/* Super class for all enchants (SUPER OF DECORATOR CLASSES) */
public abstract class Enchants extends Weapon
{
    protected Weapon next;
    public Enchants() { }
    public Enchants(Weapon next) { this.next = next; }

    @Override public String getName()
    {
        return next.getName();
    }

    @Override public abstract Item clone();
    @Override public int getCost() { return next.getCost(); }

    @Override public int getEffect() { return next.getEffect(); }

    public abstract int getEnchantCost();
    public abstract String getEnchantName();

    @Override public abstract String toShopString();
    public abstract String toEShopString();
}