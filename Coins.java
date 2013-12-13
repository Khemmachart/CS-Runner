import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Coins here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coins extends Items
{
    public static int coins;
    
    public Coins () {
        super(2,
              "/coins_g/coins_08.gif",
              "/coins_g/coins_07.gif",
              "/coins_g/coins_06.gif",
              "/coins_g/coins_05.gif",
              "/coins_g/coins_04.gif",
              "/coins_g/coins_03.gif",
              "/coins_g/coins_02.gif",
              "/coins_g/coins_01.gif");
        coins ++;
    }    
    
    public void act() 
    {
        if (!checkWorldEdge()) {
            timeKeeper();
            changeImg();
            move();
        }    
        else {
            destroy();
        }
    }
    
    public int getAmount () {
        return coins;
    }
}
