package controller;

import model.*;
/* Factory class to create enemies based off probablities */
public class EnemyFactory 
{
    private double slimeProb, goblinProb, ogreProb, dragonProb;
    //Will only be created once at start of game 
    public EnemyFactory()
    {
        this.slimeProb = 0.5;
        this.goblinProb = 0.3;
        this.ogreProb = 0.2;
        this.dragonProb = 0.0;
    }
    
    /** 
     * @param randProbability
     * @return Enemy (The newly created enemy)
     */
    public Enemy createEnemy(double randProbability)
    {
        Enemy newEnemy = null;
        if(randProbability < dragonProb) //Make Dragon
        {
            newEnemy = new Enemy("Dragon", 100, 100, 15, 30, 15, 20, 100);            
        }
        else if (randProbability < dragonProb + ogreProb) //Make Ogre
        {
            newEnemy = new Enemy("Ogre", 40, 40, 5, 10, 6, 12, 40);
        }
        else if (randProbability < dragonProb + ogreProb + goblinProb) //Make Goblin
        {
            newEnemy = new Enemy("Goblin", 30, 30, 3, 8, 4, 8, 20); 
        }
        else if (randProbability < dragonProb +  ogreProb + goblinProb + slimeProb) //Make Slime
        {
            newEnemy = new Enemy("Slime", 10, 10, 3, 5, 0, 2, 10); 
        }

        return newEnemy;
    }
    
    /** 
     * @param dragonProb
     */
    public void setDragonProb(double dragonProb) 
    {
        this.dragonProb = dragonProb;
    }

    /** 
     * @param ogreProb 
     */
    public void setOgreProb(double ogreProb) 
    {
        this.ogreProb = ogreProb;
    }

    /** 
     * @param goblinProb 
     */
    public void setGoblinProb(double goblinProb) 
    {
        this.goblinProb = goblinProb;
    }

    /** 
     * @param slimeProb
     */
    public void setSlimeProb(double slimeProb) 
    {
        this.slimeProb = slimeProb;
    }

    /** 
     * @return double
     */
    public double getDragonProb() 
    {
        return this.dragonProb;
    }

    /** 
     * @return double
     */
    public double getOgreProb() 
    {
        return this.ogreProb;
    }
    
    /** 
     * @return double
     */
    public double getGoblinProb() 
    {
        return this.goblinProb;
    }
    
    /** 
     * @return double 
     * */
    public double getSlimeProb() 
    {
        return this.slimeProb;
    }
}