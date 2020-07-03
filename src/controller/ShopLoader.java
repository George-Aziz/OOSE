package controller;

import exceptions.FileErrorException;
import model.Shop;

/* Interface for ShopLoader Strategy Pattern */
public interface ShopLoader 
{
    public int loadItems(Shop shop) throws FileErrorException;
}