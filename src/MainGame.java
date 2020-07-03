import model.*;
import view.*;
import controller.*;
import exceptions.*;

import java.util.*;

public class MainGame 
{
    
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Player player = new Player();
        Shop shop = new Shop();
        
        //CREATE VIEWS
        GeneralView generalView = new GeneralView(sc);
        ShopView shopView = new ShopView();
        PlayerView playerView = new PlayerView();
        BattleView battleView = new BattleView();

        //CREATE FACTORIES
        ItemFactory itemFactory = new ItemFactory();
        EnchantFactory enchantFactory = new EnchantFactory();
        EnemyFactory enemyFactory = new EnemyFactory();

        //Makes the Enemies and put into map so that strategy can be used in battle
        Enemy slime = new Enemy("Slime", 10, 10, 3, 5, 0, 2, 10); 
        Enemy goblin = new Enemy("Goblin", 30, 30, 3, 8, 4, 8, 20);
        Enemy ogre = new Enemy("Ogre", 40, 40, 5, 10, 6, 12, 40);
        Enemy dragon = new Enemy("Dragon", 100, 100, 15, 30, 15, 20, 100);

        //CREATE ATTACK STRATEGY FOR ENEMIES ATTACKS
        Map<String,EnemyAttack> attackStrategy = new HashMap<String,EnemyAttack>();
        attackStrategy.put(slime.getName(), new SlimeAttack(slime));
        attackStrategy.put(goblin.getName(), new GoblinAttack(goblin));
        attackStrategy.put(ogre.getName(), new OgreAttack(ogre));
        attackStrategy.put(dragon.getName(), new DragonAttack(dragon));

        //CREATE EQUIPITEM STRATEGY FOR PLAYER WHEN EQUIPS
        Map<String,EquipItem> equipItemStrategy = new HashMap<String,EquipItem>();
        equipItemStrategy.put("Armour", new EquipArmour());
        equipItemStrategy.put("Weapon", new EquipWeapon());

        //CREATES THE CONTROLLERS
        PlayerController playerController = new PlayerController(player, generalView, playerView, equipItemStrategy);
        BattleController battleController = new BattleController(enemyFactory, playerController, generalView, playerView, battleView, attackStrategy);
        ShopController shopController = new ShopController(shop, enchantFactory, generalView, shopView, playerView);
     
        //CREATES SELLING ITEMS STRATEGY FOR SHOP CONTROLLER
        Map<Integer,ShopSell> sellStrategy = new HashMap<Integer,ShopSell>();
        sellStrategy.put(1, new SellWeapon(shopController));
        sellStrategy.put(2, new SellArmour(shopController));
        sellStrategy.put(3, new SellPotion(shopController));

        GameController gc = new GameController(playerController, shopController, battleController, generalView, playerView);
        
        //FILEMANAGER AT THE MOMENT BUT COULD BE CHANGE ONCE MORE STRATEGIES ARE IMPLEMENTED
        ShopLoader shopLoader = new FileManager(itemFactory);
        try
        {
            gc.setupGame(shop, shopLoader);
            shop.addEnchant(new DamageAddTwo());
            shop.addEnchant(new DamageAddFive());
            shop.addEnchant(new PowerUp());
            shop.addEnchant(new FireDamage());
            shopController.setSellStrategy(sellStrategy);
            gc.mainMenu(player, shop);
        }
        catch (InvalidShopException e)
        {
            generalView.displayErrorMessage("Loading Shop", "Shop is invalid due to missing/invalid data!");
        }

    }
}