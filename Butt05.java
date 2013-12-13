import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Butt05 extends CharButton
{
    public Butt05 () {
        super("images/char_sheet/char_4/stand1_0.png",
             "images/char_sheet/char_4/stand1_1.png",
             "images/char_sheet/char_4/stand1_2.png",
             "images/char_sheet/char_4/stand1_3.png",
             "images/char_sheet/char_4/stand1_4.png");
    }   
    
    public void createChar(){
        ((ScrollWorld)getWorld()).createPlayer05();
    }
}
