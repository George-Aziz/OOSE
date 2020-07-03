package controller;

import model.Player;
import view.GeneralView;

/* Interface for ShopSell Strategy Pattern */
public interface ShopSell 
{
    public boolean sellItem(Player player, GeneralView view);    
}