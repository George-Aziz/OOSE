# George Aziz Turn-Based Combat Game Readme

**Date created:** 13/05/2020

**Date last modified:** 30/05/2020

### Purpose: 
* To Design and implement a simple turn-based combat game
    * To Allow player to fight in battles against enemies based off random probablity
    * To Allow player to buy items such as weapons,armour or potions in the shop 
    * To Allow player to enchant weapons through the shop 
    * To Allow player to sell items in their inventory

### Files in project: 
MainGame.java

build.xml

ShopFile.txt

**Model:**

---
    Armour.java
    BaseWeapon.java
    DamageAddFive.java
    DamageAddTwo.java
    Enchants.java
    Enemy.java
    FireDamage.java
    Item.java
    Player.java
    Potion.java
    PowerUp.java
    Shop.java
    Weapon.java
---
**Controller:**

---
    BattleController.java
    DragonAttack.java 
    EnchantFactory.java 
    EnemyAttack.java 
    EnemyFactory.java
    EquipArmour.java 
    EqipItem.java 
    EquipWeapon.java 
    FileManager.java 
    GameController.java 
    GoblinAttack.java 
    ItemFactory.java 
    OgreAttack.java 
    PlayerController.java 
    SellArmour.java 
    SellPotion.java 
    SellWeapon.java 
    ShopController.java 
    ShopLoader.java 
    ShopSell.java
    SlimeAttack.java
---
**View:**

---
    BattleView.java 
    GeneralView.java 
    PlayerView.java 
    ShopView.java 
---

**Exceptions:**

---
    FileErrorException.java 
    InvalidItemException.java 
    InvalidShopException.java
---

### Functionality:  

To compile and run do `ant` and then go into dist directory by `cd dist` and then `java -jar MainGame.java`

**NOTE**: If you would like to use your own file input, please make sure that the file is in the  resources directory and also rename the file to 'ShopFile.txt'
