import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Butt34 extends CharButton
{
    public Butt34 () {
        super("images/char_sheet/char_5/stand1_0.png",
             "images/char_sheet/char_5/stand1_1.png",
             "images/char_sheet/char_5/stand1_2.png",
             "images/char_sheet/char_5/stand1_3.png",
             "images/char_sheet/char_5/stand1_4.png");
    
    }   
    
    public void createChar(){
        ((ScrollWorld)getWorld()).createPlayer34();
    }
}
