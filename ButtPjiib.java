import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ButtChar1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ButtPjiib extends CharButton
{
    public ButtPjiib () {
        super("images/char_sheet/char_3/stand1_0.png",
              "images/char_sheet/char_3/stand1_1.png",
              "images/char_sheet/char_3/stand1_2.png",
              "images/char_sheet/char_3/stand1_3.png",
              "images/char_sheet/char_3/stand1_4.png");
    }
    
    public void createChar(){
        ((ScrollWorld)getWorld()).createPjiib();
    }
}
