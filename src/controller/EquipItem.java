package controller;

import model.Player;
import view.GeneralView;
import view.PlayerView;

public interface EquipItem 
{
    public void equipItem(Player player, GeneralView genView, PlayerView playView);   
}