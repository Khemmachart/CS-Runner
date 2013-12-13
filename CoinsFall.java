import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Coins here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CoinsFall extends Items
{
    public static int coinsFall;
    
    public CoinsFall () {
        super(2,
              "/coins_s/coins_08.gif",
              "/coins_s/coins_07.gif",
              "/coins_s/coins_06.gif",
              "/coins_s/coins_05.gif",
              "/coins_s/coins_04.gif",
              "/coins_s/coins_03.gif",
              "/coins_s/coins_02.gif",
              "/coins_s/coins_01.gif");
        coinsFall ++;
    }    
    
    public int getAmount () {
        return coinsFall;
    }
}
