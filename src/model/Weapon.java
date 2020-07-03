package model;
/* Super class for Weapon to allow decorator pattern to be implemented */
public abstract class Weapon implements Item
{
    @Override public abstract Item clone();
    @Override public abstract String getName();
    @Override public abstract int getCost();
    @Override public abstract int getEffect();

    @Override public abstract String toShopString(); 
    @Override public abstract String toString();
   
    @Override public void addToPlayer(Player player) { player.addWeapon(this); };
    @Override public void removeFromPlayer(Player player) { player.removeWeapon(this); }
     
}